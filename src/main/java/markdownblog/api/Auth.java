package markdownblog.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.inject.hk2.RequestContext;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Login {
    public String userId = "";
    public String userPassword = "";
    public String userName = "";
}

@Path("auth")
public class Auth {
    @POST
    @Produces("application/json")
//A producer method acts as a source of objects to be injected, where:
//• the objects to be injected are not required to be instances of beans, or
//• the concrete type of the objects to be injected may vary at runtime, or
//• the objects require some custom initialization that is not performed by the bean constructor.
    @Path("login")
    public Response login(Login loginData, @Context HttpServletRequest request) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");
            PreparedStatement preparedStatement = conn.prepareStatement(" SELECT * from dbo.[User] WHERE (userId = ?) ");
            //login物件
            preparedStatement.setString(1, loginData.userId);
            ResultSet result = preparedStatement.executeQuery();


            if (request.getSession(false) != null) {
                request.changeSessionId();
                System.out.println(request.changeSessionId());
            }
            request.getSession().setAttribute("login", loginData.userId);


            if (result.next()) {
                System.out.print(result.getString("userPassword"));
                System.out.print(loginData.userPassword);
                if (loginData.userPassword.equals(result.getString("userPassword"))) {
                    return Response.ok().build();    // 200を返す
                }
            }
            return Response.status(403).build();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Response.status(500).build();
    }

    @POST
    @Produces("application/json")
    @Path("logout")
    public void logout(@Context HttpServletRequest request) {
        request.getSession().invalidate();
        System.out.println("user log out ");

    }

    @POST
    @Produces("application/json")
    @Path("modify")
    public Response modify(Login loginData, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        loginData.userId = (String) session.getAttribute("login");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");

            PreparedStatement preparedStatement = conn.prepareStatement(" UPDATE dbo.[User] SET  userName = ?, userPassword= ? , modifyTime = GETDATE() WHERE userId = ? ");


            preparedStatement.setString(1, loginData.userName);
            preparedStatement.setString(2, loginData.userPassword);
            preparedStatement.setString(3, loginData.userId);
            preparedStatement.executeUpdate();
            System.out.println("Data had been modified");
            return Response.ok().build();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    @POST
    @Produces("application/json")
    @Path("register")
    public Response register(Login loginData, @Context HttpServletRequest request) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");

            PreparedStatement preparedStatement = conn.prepareStatement(" INSERT INTO dbo.[User] ( userId , userName , userPassword , modifyTime) VALUES  ( ? , ? , ? , GETDATE() )  ");

            preparedStatement.setString(1, loginData.userId);
            preparedStatement.setString(2, loginData.userName);
            preparedStatement.setString(3, loginData.userPassword);

            preparedStatement.executeUpdate();
            System.out.println("User had been registered." + loginData.userId + " " + loginData.userName + " " + loginData.userPassword);
            return Response.ok().build();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    @GET
    @Produces("application/json")
    @Path("userdata")
    public Login userdata(@Context HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Login userdata = new Login();

        userdata.userId = (String) session.getAttribute("login");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");

            PreparedStatement preparedStatement = conn.prepareStatement(" SELECT * FROM dbo.[User] WHERE userId = ?  ");

            preparedStatement.setString(1, userdata.userId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                userdata.userName = result.getString("userName");
                return userdata;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }


        System.out.println("id: " + userdata.userId + "Name: " + userdata.userName);

        return userdata;

    }

}