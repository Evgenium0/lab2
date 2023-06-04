import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler
{
    private Connection connection;

    public DatabaseHandler(String url, String username, String password) throws SQLException
    {
        connection = DriverManager.getConnection(url, username, password);
    }
    public User getUser(String username, String password) throws SQLException
    {
        String query = "SELECT id, password_hash FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    String passwordHash = resultSet.getString("password_hash");
                    if (checkPassword(password, passwordHash))
                    {
                        int id = resultSet.getInt("id");
                        return new User(id, username, passwordHash);
                    }
                }
                return null;
            }
        }
    }

    public boolean addUser(String username, String password) throws SQLException
    {
        String query = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        String passwordHash = hashPassword(password);
        try (PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }
    }
    private boolean checkPassword(String password, String passwordHash)
    {
        String hashedPassword = hashPassword(password);
        return hashedPassword.equals(passwordHash);
    }
    private String hashPassword(String password)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void close() throws SQLException
    {
        connection.close();
    }
}
