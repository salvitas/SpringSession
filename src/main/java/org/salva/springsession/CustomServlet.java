package org.salva.springsession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * [Class description.  The first sentence should be a meaningful summary of the class since it
 * will be displayed as the class summary on the Javadoc package page.]
 *
 * @author <a href="mailto:salva@backbase.com">salva</a>
 * @since 25/05/16
 */
@WebServlet("/customServlet")
public class CustomServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userName = request.getParameter("userName");

        if (userName != null) {
            session.setAttribute("Username", request.getParameter("userName"));
        }

        MultiLoginService.createMultiLogin(request);

        RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");

        rd.forward(request, response);
    }
}

