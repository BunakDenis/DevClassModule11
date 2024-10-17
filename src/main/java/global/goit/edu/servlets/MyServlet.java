package global.goit.edu.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MyServlet", urlPatterns = {"/", "/storeUser"})
public class MyServlet extends HttpServlet {

    private TemplateEngine templateEngine;

/*    @Override
    public void init() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        WebContext context = new WebContext((IWebExchange) getServletContext(), req.getLocale());
        context.setVariable("username", username);
        templateEngine.process("index", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");

        if (username != null && !username.trim().isEmpty()) {
            req.getSession().setAttribute("username", username);
        }

        resp.sendRedirect("/");
    }
}