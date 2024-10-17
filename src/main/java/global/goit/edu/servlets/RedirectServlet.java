package global.goit.edu.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/search")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String googleURL = "https://www.google.com/search";

        if (req.getParameterMap().containsKey("q")) {

            String parameter = req.getParameterValues("q")[0];

            googleURL += "?q=" + parameter;

        }
        resp.sendRedirect(googleURL);
    }
}
