package markdownblog.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UserModify", value = "/UserModify")
public class UserModify extends HttpServlet {
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
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("login");
//        System.out.println(userId+userName+userPassword);
        storeUserData(userName,userPassword,userId);
        out = response.getWriter();
        out.println("<html><body>");
        out.println("Data had been modified.");
        out.println("<a href=\"index.jsp\">Go back to landing page</a>");
        out.println("</body></html>");
        out.close();
        request.getRequestDispatcher("User").forward(request, response);
        //notworking...
    }

    private void initializeJDBC(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded...");

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;","tsai","tsai1999");
            System.out.println("Database connected...");
            preparedStatement = conn.prepareStatement(" UPDATE dbo.[User] SET  userName = ?, userPassword= ? , modifyTime = GETDATE() WHERE userId = ? ");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void storeUserData(String userName, String userPassword, String userId) {
        try {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
