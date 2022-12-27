package markdownblog.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class MarkdownData {
    public String id = "";
    public String title = "";
    public String content = "";
}

@Path("markdown")
public class Markdown {
    @GET
    @Produces("application/json")
    @Path("{id}")
    public MarkdownData getMarkdown(@PathParam("id") String id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Markdown;encrypt=true;trustServerCertificate=true;","tsai","tsai1999");
            PreparedStatement preparedStatement = conn.prepareStatement(" SELECT * from dbo.[Markdown] WHERE (id = ?) ");
            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                MarkdownData markdown = new MarkdownData();
                markdown.id = result.getString("id");
                markdown.title = result.getString("title");
                markdown.content = result.getString("content");
                return markdown;
            }
        }  catch(SQLException e) {
            e.printStackTrace();
        }
        return new MarkdownData();
    }
}