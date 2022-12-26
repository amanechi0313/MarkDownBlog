package markdownblog.controller;

import Bean.UserBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

@WebServlet(name = "UserLogin", value = "/UserLogin")
public class UserLogin extends HttpServlet {
    private final String SUCCESS_PATH = "admin.jsp";
    private final String ERROR_PATH = "index.jsp";
    private PreparedStatement preparedStatement;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");
        response.sendRedirect(login(userId,userPassword)?SUCCESS_PATH:ERROR_PATH);
    }

    private boolean login(String userId, String userPassword){

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded...");

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;","tsai","tsai1999");
            System.out.println("Database connected...");

            preparedStatement = conn.prepareStatement(" SELECT*FROM [User] WHERE userId = ? ");
            preparedStatement.setString(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            UserBean user = new UserBean();

            while (rs.next()) {

                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setModifyTime(rs.getString("modifyTime"));
            }

            if(Objects.equals(user.getUserPassword(), userPassword)){
                System.out.println("true");
                return true;

            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("false");
        return false;
    }
}
