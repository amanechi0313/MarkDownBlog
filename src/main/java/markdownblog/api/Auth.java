package markdownblog.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Login {
    public String userId = "";
    public String userPassword = "";
}

@Path("auth")
public class Auth {
    @POST
    @Produces("application/json")
    @Path("login")
    public Response login(Login loginData,@Context HttpServletRequest request) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=MarkDown;encrypt=true;trustServerCertificate=true;","tsai","tsai1999");
            PreparedStatement preparedStatement = conn.prepareStatement(" SELECT * from dbo.[User] WHERE (userId = ?) ");
            //login物件
            preparedStatement.setString(1, loginData.userId);
            ResultSet result = preparedStatement.executeQuery();


                if(request.getSession(false)!=null){
                    request.changeSessionId();
                System.out.println(request.changeSessionId());
                }
                request.getSession().setAttribute("login",loginData.userId);




            if (result.next()) {
                System.out.print(result.getString("userPassword"));
                System.out.print(loginData.userPassword);
                if (loginData.userPassword.equals(result.getString("userPassword"))) {
                    return Response.ok().build();    // 200を返す
                }
            }
            return Response.status(403).build();
        } catch(SQLException e) {
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


}