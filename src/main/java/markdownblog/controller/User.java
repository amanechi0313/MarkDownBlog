package markdownblog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "User", value = "/User")
public class User extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Object> token = Optional.ofNullable(session.getAttribute("login"));

        if(token.isPresent()){
            System.out.println("through User to admin. TOKEN: " + token );
            System.out.println(session.getAttribute("login"));
            response.sendRedirect("admin.jsp");
//            request.getRequestDispatcher("admin.jsp").forward(request,response);
        }else {
            response.sendRedirect("index.jsp");
        }

    }
}
