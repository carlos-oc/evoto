package es.upm.dit.isst.evoto.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

/**
 * Modelo para generar y almacenar el certificado de la CA en la base de datos y para generar los certificados 
 * de cliente solicitados.
 * Tiene como par�metro un id pero a la hora de la implementaci�n s�lo va a haber un certificado almacenado
 * en la base de datos con id = 1
 * El certificado se almacena en la base de datos en un array de bytes codificado en base 64
 *  
 * @author Carlos Ortego Casado
 *
 */
@Entity
public class CertificadoCA implements Serializable{
	private static final long serialVersionUID = 01L;
	
	@Id
	@Column
	private Long id;
	@Column
	private byte[] certificado;
	/**
	 * Constructor de la clase Certificado CA
	 */
	public CertificadoCA (){
		try{
			long id = 1;
			Security.addProvider(new BouncyCastleProvider());			
			// Obtener la clave p�blica y la clave privada
	        PrivateKey caPrivKey = getPrK();
	        PublicKey caPubKey = getPuK();
	        // Generar y guardar certificado asignando el id
	        byte[] cert = x509ToBos(createMasterCert(caPubKey, caPrivKey));
	        this.certificado = cert;
	        this.id = id;
	        
		} catch(Exception e){
			System.out.println(e);
		}
	}
	/**
	 * Getter del id del certificado con el que se ha guardado en la base de datos
	 * @return el id del certificado
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Getter para obtener el certificado
	 * @return el certificado x509
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public X509Certificate getCertificado() throws ClassNotFoundException, IOException {
		return bosToX509(certificado);
	}
	/**
	 * Genera un certificado de CA
	 * @param pubKey clave p�blica de la CA
	 * @param privKey clave privada de la CA
	 * @return el certificado de la CA
	 * @throws Exception
	 */
	public static X509Certificate createMasterCert(PublicKey pubKey, PrivateKey privKey) throws Exception {
		Date notBefore = new Date(System.currentTimeMillis() - 86400000L * 365); // Inicio de validez
		Date notAfter = new Date(System.currentTimeMillis() + 86400000L * 365 * 100); // fin de validez
		BigInteger serial = BigInteger.valueOf(1); //serial
		// signers name
		String issuer = "C=ES, O=ISST, OU=eVoto, L=Madrid, ST=Madrid, CN=Grupo1";
		// subjects name - the same as we are self signed.
		String subject = "C=ES, O=ISST, OU=eVoto, L=Madrid, ST=Madrid, CN=Grupo1";
		// create the certificate - version 3
		X509v3CertificateBuilder v3Bldr = new JcaX509v3CertificateBuilder(new X500Name(issuer), serial,
				notBefore,notAfter, new X500Name(subject), pubKey);
		
		X509CertificateHolder certHldr = v3Bldr.build(new JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(privKey));
		
		// Extension: Longitud del path m�ximo (Los certificados generados con este no podran generar certificados)
        v3Bldr.addExtension(Extension.basicConstraints, true, new BasicConstraints(0));
		X509Certificate cert = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certHldr);

		cert.checkValidity(new Date());

		cert.verify(pubKey);

		return cert;
	}
	/**
	 * Pasa de certificado x509 a bytes para poder guardarlo en la base de datos
	 * @param cert certificdo x509 a pasar a bytes
	 * @return los bytes resultantes para guardar en BD
	 * @throws IOException
	 */
	private byte[] x509ToBos  (X509Certificate cert) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);   
		out.writeObject(cert);
		byte[] data = bos.toByteArray(); 
		bos.close();
		return data;
	}
	/**
	 * Pasa de bytes a certificado x509 para poder generar el certificado de los bytes almacenados en la base de datos
	 * @param bos bytes con los datos del certificado almacenados en BD
	 * @return el certificado x509
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private X509Certificate bosToX509(byte[] bos) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(bos);
		ObjectInput in = new ObjectInputStream(bis);
		X509Certificate cert = (X509Certificate) in.readObject(); 
		bis.close();
		return cert;
	}
	/**
	 * Devuelve un certificado x509 firmado por la CA para un cliente
	 * @param nombre nombre del cliente
	 * @param apellido1 primer apellido del cliente 
	 * @param apellido2 segundo apellido del cliente
	 * @param dni dni del cliente
	 * @param email email del cliente
	 * @param provincia provincia del cliente
	 * @param clavePublica clave p�blica generada por el cliente
	 * @return el certificado x509 firmado por la CA
	 * @throws Exception
	 */
	public X509Certificate crearCertificadoCliente(String nombre, String apellido1, String apellido2, 
			String dni, String email, String provincia, PublicKey clavePublica) throws Exception {
		
		X509Certificate caCert = this.getCertificado(); // Certificado CA
		X509Certificate certCliente= null; // Certificado a entregar
		
		try{
			Date notBefore = new Date(System.currentTimeMillis() - 86400000L * 365); // Fecha de inicio de validez
			Date notAfter = new Date(System.currentTimeMillis() + 86400000L * 365); // Fecha de find e validez
			BigInteger serial = BigInteger.valueOf(System.currentTimeMillis()); //Serial
	
	        // Subject
	        X500NameBuilder subjectBuilder = new X500NameBuilder();
	        subjectBuilder.addRDN(BCStyle.C, "ES");
	        subjectBuilder.addRDN(BCStyle.L, provincia);
	        subjectBuilder.addRDN(BCStyle.CN, nombre + " " + apellido1 + " " + apellido2 + " (" + dni + ")");
	        subjectBuilder.addRDN(BCStyle.EmailAddress, email);
	        
			// Crear certificado v3
			X509v3CertificateBuilder v3Bldr = new JcaX509v3CertificateBuilder(caCert, serial,
		            notBefore, notAfter, subjectBuilder.build(), clavePublica);
	
	        // Extensiones
			JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();
			v3Bldr.addExtension(Extension.subjectKeyIdentifier, false, extUtils.createSubjectKeyIdentifier(clavePublica));
	        v3Bldr.addExtension(Extension.authorityKeyIdentifier, false, extUtils.createAuthorityKeyIdentifier(caCert));
	        
	        X509CertificateHolder certHldr = v3Bldr.build(new JcaContentSignerBuilder("SHA256WithRSA").setProvider("BC").build(this.getPrK()));
	        certCliente = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certHldr);
	
			certCliente.checkValidity(new Date());
	
			certCliente.verify(caCert.getPublicKey());
			
			return certCliente;
			
		} catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	/**
	 * M�todo para obtener el RSAPrivateCrtKeySpec usado para obtener la clave privada de la CA
	 * @return RSAPrivateCrtKeySpec para obtener la clave privada de la CA
	 */
	private RSAPrivateCrtKeySpec getPrSpec(){
//		RSAPrivateCrtKeySpec   caPrivKeySpec = new RSAPrivateCrtKeySpec(
//	            new BigInteger("d92c4991d76bf6aa29d2c2a7f87ac5f16eb64fafbbd83d1817b1fe022604f80c5c3feb227673b38f278d765df951ec240091e9ed7646d3daaa9addd443d53b2e58904018ae7962d93b6a0684fb0839f3226104f34801197dd189a390abf3e5a6091607db7baafbe5fa0df22a8ac4854f309f2c906c407bb5e04b80a950b58621", 16),
//	            new BigInteger("10001", 16),
//	            new BigInteger("b0b8b7a332ddd852df01484c3d51f64bf87151cba8b5daa4ec601f5d5079b2424377075bf103bc28aaa87d3a7d7bb4f679a1d5f6bc1429d785a0844ff9cdf9a6bca7e4a6daf4c35d27f24869ed1e0bde90ce5c071f32da39bb0a7211768811c1815a5fa3441fc62278e5219dbcbb2441b130578a72fe06f6d371128bddb57f", 16),
//	            new BigInteger("fab0956efffcb86a6918777705b923dd1900571a050540908a60bfe2f751541e76d1b1ec954c9f5b66123f94adee51d89f0b85cca63cc71a78c79765873e7173", 16),
//	            new BigInteger("ddc5f3bc502755a8ee19cce6ebc8370ce9ff2d029f91096e0ea8103ccfb0593b6b83972f9dfc6cc349997386a9c62edd71e4f51730ed3b5d2f127edc4622751b", 16),
//	            new BigInteger("d6fb5c0b26c80936680e2d550ab96cc20cd0b7e17e70b49fbfab9ca2fd48e784808cefb25d8eaa126d8434b6423a60420e928919a96df1a907eed22ca540ad75", 16),
//	            new BigInteger("6f532910c4a39a2a5cab72e21045c6eca2a1c77463a928b4a882d0f3d5c5ce6537892ce1684b0845d7b241fdbd4790caf49b5c34b208c41df8bd2e1560d77735", 16),
//	            new BigInteger("9a9280ee287dc02da24cd9ffcc7e69082ac2d9edde8e58ac8a7e20b18c2c69f9f5c045109c410e3f648874e8bc8eb7d68f7e82391f0ef2dadb78c04cbc3971cd", 16));
		RSAPrivateCrtKeySpec   caPrivKeySpec = new RSAPrivateCrtKeySpec(
	            new BigInteger("de55a7d04decbfcb33ff62d7973f308661b6500bbf11f72d2076e4d18671412311e496f450b5e8eb6b17a30f0370ea414a5c73e79f8e8c9c3bacc779a33ee448a283b311c5831f26d20c157333938bc7e875b634b5a2aaa746caf7d367c4c1f59e0cc053ffced5d1df9418f1b03f89f1b07eb004cc05c0e95812f81fcbc7a6590ac7c38712ee1885447a64d23488b439f06f84eb52a5c43a8ed6ae986a1f6e9011bcb5c9c3d4bc3637a248abb96ec633719492b2d1a831fa15cbed5cfefd54f71d63b2416e37fe113253ae480046577ac43f8919c99896b18a31e0d774122cff7bbcea05e456e1d3ce9c8e01096e009bcc791fa44258c643a3f767907f6aa589", 16),
	            new BigInteger("10001", 16),
	            new BigInteger("a3620e8fe4b3b5bd7dd88eaa1f5b302d906089321896fb2d0e4e50982853d1aa13dc0792423d6b8c94862a2d3e3a1beb1eda11d2844e1cc6329425821d9479ed667fa177cdcd2d7739a3e79f60a76c70db42a542d6b752f0d9f50d692d3849d054f4c9e0f07aee65ec06c8cdd2d6de67dd6b3bae4abf6fa40a34e661773e9fde2fb9df53e1748b8292d683e0dff71382d4a72e58d3a9e535532df5cf739315664b9854a02d7e026fa67198678053c547c882fd83ab29488cc0e0b0f21094718d56590da6a6fcef11aee2cf775e515b0562eee71e29a77707c4d5815f837b62f7e456e6702fecf4522e2a9beda2792033d82cb74e51ad5aebdef4cc36827e3881", 16),
	            new BigInteger("ff656c5871d0b6ba8d47d3f4c8eb63df0c4c096cffb391d66450ecdf140e013b6bbcfb1c971196e6d7a2f273f7a96647b25cd8d3554b42cfc3667332fb5788175296c0ac3360585c6df0a8c8e7ece14779238de2910e861d7085c50187f76c6bdd4ab5bd45e91f317e33dc84439cc90f0d248397847cfe517c7b4dc845282cab", 16),
	            new BigInteger("dedc38d4d0047a80e6b12b5c76e24554b963ef294373d9c6038d5d09e9df07a570c3da5ad5d9dabbd9f3c6b0bc85e5d01e13767a66857bb8799e321cbd058e1912e38bb1ddf2ea84cf5597a49d26a327e857f91ae5291ab419db5d0acc27229f2598c273065ff5ab25640c058a962d9d752a009ae5af516c25f347fd3872ce9b", 16),
	            new BigInteger("1d402e828ab68ed71aeb49195611afa49231fb3a62a6dc6323a6291afb9f0701193bd1beda868733f52a927e39b54b2397080f45551583976c17c5c5e707856cb666d1b66743f79ace74abc026dff88549188b5d91a38a34c0f87131d5b2bed633c824bc82cf4a654ec0fdd6a02fcacf5080081a4870506860cb768323789075", 16),
	            new BigInteger("f2e10f0edfa601dc798e9cb328dd70b1e55a044bf4374e1f7fbf90a63d3bd41979cbf92d05e3677f53095a8142f4aa8126460fd955e223f3e8978005d1fb3be217b3e58af358ca3df3b8548c12b9091775206b5be463e7b66077b1ef128f942bddcd9e1dbdd4d6e8aed75a42afc6fbef9ec127e7a86a69527409af6b4919d61", 16),
	            new BigInteger("ce6ce0d542632b3bb733f6152a74b7d80ed8ed0afea3953981e2bbdf8f5d05df6bb5d25584990ab6720be954899a3e375b2d7613de89fda4887cf596bb61c7517e46acff9cc955765ec4fdf0e369e34b9a027213044a62aa4616d0aeddef154a80f341658ab291865ee447218eeb8f8e6904a0cb303a80fc922145315e8c5b50", 16));
	   return caPrivKeySpec;
	}
	/**
	 * M�todo para obtener el RSAPublicKeySpec usado para obtener la clave p�blica de la CA
	 * @return RSAPublicKeySpec para obtener la clave p�blica de la CA
	 */
	private RSAPublicKeySpec getPuSpec(){
		RSAPublicKeySpec caPubKeySpec = new RSAPublicKeySpec(
	            new BigInteger("de55a7d04decbfcb33ff62d7973f308661b6500bbf11f72d2076e4d18671412311e496f450b5e8eb6b17a30f0370ea414a5c73e79f8e8c9c3bacc779a33ee448a283b311c5831f26d20c157333938bc7e875b634b5a2aaa746caf7d367c4c1f59e0cc053ffced5d1df9418f1b03f89f1b07eb004cc05c0e95812f81fcbc7a6590ac7c38712ee1885447a64d23488b439f06f84eb52a5c43a8ed6ae986a1f6e9011bcb5c9c3d4bc3637a248abb96ec633719492b2d1a831fa15cbed5cfefd54f71d63b2416e37fe113253ae480046577ac43f8919c99896b18a31e0d774122cff7bbcea05e456e1d3ce9c8e01096e009bcc791fa44258c643a3f767907f6aa589", 16),
	            new BigInteger("10001", 16));
		return caPubKeySpec;
	}
	/**
	 * Obtener clave privada de la CA
	 * @return la clave privada de la CA
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 */
	private PrivateKey getPrK() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
		KeyFactory fact = KeyFactory.getInstance("RSA", "BC");
	    PrivateKey caPrivKey = fact.generatePrivate(getPrSpec());
		return caPrivKey;
	}
	
	/**
	 * Obtener la clave p�blica de la CA
	 * @return la clave p�blica de la CA
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 */
	private PublicKey getPuK() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
		KeyFactory fact = KeyFactory.getInstance("RSA", "BC");
        PublicKey caPubKey = fact.generatePublic(getPuSpec());
		return caPubKey;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CertificadoCA [id=" + id + ", certificado=" + certificado + "]";
	}
}
