package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Matthew on 28-Dec-16.
 */
@WebServlet("/Login")
public class Login extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {
        String ServUsername = request.getParameter("username");
        String ServPassword = request.getParameter("password");

        if(ServUsername.equals("Affiliate1") && ServPassword.equals("Password1"))
        {
            HttpSession session = request.getSession();
            session.setAttribute("username",ServUsername);
            response.sendRedirect("welcome.jsp");
        }
        else
        {
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {

    }
}
