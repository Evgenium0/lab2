import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        // Получаем текущую сессию
        HttpSession session = httpRequest.getSession(false);

        // Проверяем, аутентифицирован ли пользователь (проверяем, есть ли в сессии атрибут "username")
        boolean isAuthenticated = (session != null && session.getAttribute("username") != null);

        if (isAuthenticated)
        {
            // Если пользователь аутентифицирован, продолжаем выполнение запроса
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            // Если пользователь не аутентифицирован, перенаправляем его на страницу логина
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    // Для этого фильтра не нужно реализовывать методы init() и destroy()
}
