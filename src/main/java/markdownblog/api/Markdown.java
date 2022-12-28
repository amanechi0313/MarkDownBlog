package markdownblog.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class MarkdownData {
    public String userId = "";
    public int articleId;
    public String articleTitle = "";
    public String articleContent = "";
    public String saveTime = "";
    public String tags = "";
}

@Path("markdown")
public class Markdown {
    @GET
    @Produces("application/json")
    @Path("/editor/{articleId}")
    public MarkdownData getMarkdown(@PathParam("articleId") String articleId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Markdown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");
            PreparedStatement preparedStatement = conn.prepareStatement(" SELECT * from dbo.[Markdown] WHERE (articleId = ?) ");

            preparedStatement.setString(1, articleId);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                MarkdownData markdown = new MarkdownData();

                return markdown;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new MarkdownData();
    }

    @GET
    @Produces("application/json")
    @Path("articles")
    public ArrayList<MarkdownData> getarticles(@Context HttpServletRequest request) {
        ArrayList<MarkdownData> articles = new ArrayList<>();
        try {
            HttpSession session = request.getSession();

            String userId = (String) session.getAttribute("login");

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Markdown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");
            PreparedStatement preparedStatement = conn.prepareStatement(" SELECT * from dbo.[Article] WHERE (userId = ?) ");
            preparedStatement.setString(1, userId);

            ResultSet result = preparedStatement.executeQuery();


            while (result.next())
            {
                MarkdownData markdown = new MarkdownData();
                markdown.userId = userId;
                markdown.articleId = result.getInt("articleId");
                markdown.articleContent = result.getString("articleContent");
                markdown.articleTitle = result.getString("articleTitle");
                markdown.saveTime = result.getString("saveTime");
                markdown.tags = result.getString("tags");
                articles.add(markdown);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }

    @POST
    @Produces("application/json")
    @Path("articles")
    public int newarticle(MarkdownData article, @Context HttpServletRequest request) {
        System.out.println("through api");
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("login");

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Markdown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");
            PreparedStatement query = conn.prepareStatement("SELECT Max(articleId) FROM Article");
            ResultSet rs = query.executeQuery();

            int articleId = 0;

            if(rs.next()){
                articleId =  rs.getInt(1)+1;
            }
            System.out.println("articleId is " + articleId);
            PreparedStatement preparedStatement = conn.prepareStatement(" INSERT INTO dbo.[Article] (userId, articleId ,articleContent, saveTime, tags, articleTitle) values (?, ? ,? , GETDATE() , ? , ? ) ");

            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, articleId);
            preparedStatement.setString(3, article.articleContent);
            preparedStatement.setString(4, article.tags);
            preparedStatement.setString(5, article.articleTitle);

            preparedStatement.executeUpdate();
            System.out.println("article newed");
            session.setAttribute("articleId",articleId);
            return articleId;
//            return  Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return Response.status(500).build();
        return 0;//...not okay
    }
    @POST
    @Produces("application/json")
    @Path("save")
    public int sacearticle(MarkdownData article, @Context HttpServletRequest request) {
        System.out.println("through save api");
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("login");

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Markdown;encrypt=true;trustServerCertificate=true;", "tsai", "tsai1999");
            PreparedStatement preparedStatement = conn.prepareStatement("");


//            return  Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return Response.status(500).build();
        return 0;//...not okay
    }
}