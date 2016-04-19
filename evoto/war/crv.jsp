<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
  <head>
  
    <%@include file="inc/head.html" %>
    
    <style type="text/css">
      .chart-legend li span{
      display: inline-block;
      width: 12px;
      height: 12px;
      margin-right: 5px;
      }
      .chart-legend li {
        display: inline;
        margin-right: 10px;
      }
    </style>
  </head>
  <body class="hold-transition skin-purple sidebar-mini">
    <div class="wrapper">

		<%@include file="inc/header.html" %>
      
		<%@include file="inc/sidebar.html" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Elecciones al senado
            <small>Centro de recuento de votos</small>
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- left column -->
            <div class="col-md-7">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Gráfica de resultados</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <h2 class="text-center"><strong>ELECCIONES GENERALES 2015</strong></h2>
                    <h3 class="text-center">Escaños</h3>
                    <canvas id="pieChart"></canvas>
                    <p>&nbsp; </p>
                    <div id="js-legend" class="chart-legend"></div>
                    <p>&nbsp; </p>
                </div>
              </div><!-- /.box --> 
            </div><!--/.col -->
            <div class="col-md-5">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Recuento de votos</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <h4 class="text-center"><strong>Resumen del escrutinio Total de España<br/>2015</strong></h4>
                  <table class="table table-bordered">
                    <tbody>
                      <tr>
                      <th class="info">Escrutado:</th>
                      <td colspan="2">99.9 %</td>
                      </tr>
                      <tr>
                      <th class="info">Esca&ntilde;os totales:</th>
                      <td class="tipoNumeroElegir" colspan="2">208</td>
                      </tr>
                      <tr>
                      <th class="info">Votos contabilizados:</th>
                      <td class="tipoNumero">24867744</td>
                      <td>71.88 %</td>
                      </tr>
                      <tr>
                      <th class="info">Abstenciones:</th>
                      <td class="tipoNumero">9730667</td>
                      <td>28.12 %</td>
                      </tr>
                      <tr>
                      <th class="info">Votos nulos:</th>
                      <td class="tipoNumero">800853</td>
                      <td>3.22 %</td>
                      </tr>
                      <tr>
                      <th class="info">Votos en blanco:</th>
                      <td class="tipoNumero">978313</td>
                      <td>4.06 %</td>
                      </tr>
                    </tbody>
                    </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
            <div class="col-md-8 col-md-offset-2">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Recuento de votos</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <h4 class="text-center"><strong>Votos por senadores total en España</strong></h4>
                    <table class="table table-bordered table-striped">
                      <tbody>
                        <tr>
                          <th class="info">Partido</th>
                          <th class="info">Esca&ntilde;os</th>
                          <th class="info" colspan="2">Votos</th>
                        </tr>
                        <tr>
                          <th><acronym title="PARTIDO POPULAR">PP</acronym></th>
                          <td>124</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="PARTIDO SOCIALISTA OBRERO ESPAÃOL">PSOE</acronym></th>
                          <td>47</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>PODEMOS</th>
                          <td>16</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>ERC-CATSI</th>
                          <td>6</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>DL</th>
                          <td>6</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="EUZKO ALDERDI JELTZALEA-PARTIDO NACIONALISTA VASCO">PNV</acronym></th>
                          <td>6</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>CCa-PNC</th>
                          <td>1</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>CAMBIO-ALDAKETA</th>
                          <td>1</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>ASG</th>
                          <td>1</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>UxGC</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>UNIDAD DEL PUEBLO Y ALTERNATIVA REPUBLICANA</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>PAÃS VALENCIÃ</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>EU-eX</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>UNIDAD POPULAR EN COMÃN</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>AJU</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>VOX</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>SOLUCIONA</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="SOLIDARIDAD Y AUTOGESTION INTERNACIONALISTA">SAIn</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>SOMVAL</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>unio.cat</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>UPYD</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>RECORTES CERO-GRUPO VERDE</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>SOM MENORCA</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>ESQ</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>EN POSITIU</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>Independientes-F.I.A.</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>NÃS</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>AVANT</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>PARTICIPACIÃN CIUDADANA</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>MÃS</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="POR UN MUNDO MÃS JUSTO">PUM+J</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="ESCAÃOS EN BLANCO/ESCONS EN BLANC">EB</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>EL PI</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>EH Bildu</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="FAMILIA Y VIDA">PFyV</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="FALANGE ESPAÃOLA DE LAS J.O.N.S.">FE de las JONS</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="DEMOCRACIA NACIONAL">D.N.</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>C's</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>BAR</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>BADAJOZ ADELANTE</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="CIUDADANOS DE CENTRO DEMOCRÃTICO">CCD</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>CILUS</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>CRA</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>FDEE</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="INICIATIVA FEMINISTA">I.Fem</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="PARTIDO DE LA LIBERTAD INDIVIDUAL">P-LIB</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="PARTIDO HUMANISTA">PH</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="PARTIDO REGIONALISTA DEL PAIS LEONES">PREPAL</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>PT</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="PARTIT COMUNISTA DELS POBLES D'ESPANYA">P.C.P.E.</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>LIBERTATE NAFARRA</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>I.M.C.</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>CENTRO MODERADO</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th>PARTIDO POLÃTICO</th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                        <tr>
                          <th><acronym title="PARTIT ANIMALISTA CONTRA EL MALTRACTAMENT ANIMAL">PACMA</acronym></th>
                          <td>0</td>
                          <td>-</td>
                          <td>- %</td>
                        </tr>
                      </tbody>
                    </table>
            </div>
          </div>   <!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	<%@include file="inc/footer.html" %>
	
    </div><!-- ./wrapper -->
	<%@include file="inc/scripts.html" %>
	<%@include file="inc/scripts-crv.html" %>
  </body>
</html>
