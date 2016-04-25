package asdfsd;

import java.io.IOException;
import javax.servlet.http.*;
import org.bouncycastle.mozilla.jcajce.JcaSignedPublicKeyAndChallenge;

@SuppressWarnings("serial")
public class AsffasServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
