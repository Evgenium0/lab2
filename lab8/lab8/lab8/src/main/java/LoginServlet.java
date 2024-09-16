import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt; // импортируем класс BCrypt
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public void init() throws ServletException
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=forLab8";
            String user = "sa";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e) {
            throw new ServletException("Unable to load JDBC driver.", e);
        }
        catch (SQLException e) {
            throw new ServletException("Unable to connect to database.", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Проверяем, есть ли пользователь с таким именем и паролем в базе данных
        boolean isAuthenticated = authenticate(username, password);

        if (isAuthenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            response.sendRedirect(request.getContextPath() + "/home.jsp");
        }
        else
        {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    private boolean authenticate(String username, String password)
    {
        // Получаем хэш пароля пользователя из базы данных
        String passwordHash = getPasswordHashByUsername(username);

        if (passwordHash != null)
        {
            // Проверяем, соответствует ли введенный пароль хэшу, хранящемуся в базе данных
            return BCrypt.checkpw(password, passwordHash); // используем метод checkpw() класса BCrypt
        }
        else
        {
            return false;
        }
    }
    private String getPasswordHashByUsername(String username)
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement("SELECT password_hash FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return rs.getString("password_hash");
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}