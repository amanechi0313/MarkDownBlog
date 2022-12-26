package markdownblog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "PostArticle", value = "/PostArticle")
public class PostArticle extends HttpServlet {
    private PreparedStatement preparedStatement;
    PrintWriter out;
    public void init() throws ServletException{
        initializeJDBC();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        System.out.println(bookId+name+price+author);

        storeBook(Integer.parseInt(bookId), name, Integer.parseInt(price), author);
        out = response.getWriter();

        out.println("Book " + name + " has been stored in the database.");
        out.close();
    }

    private void initializeJDBC(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded...");

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;","tsai","tsai1999");
            System.out.println("Database connected...");
            preparedStatement = conn.prepareStatement("");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void storeBook(int id, String name, int price, String author) {
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, price);
            preparedStatement.setString(4, author);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}