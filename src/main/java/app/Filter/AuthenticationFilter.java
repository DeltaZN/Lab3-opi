package app.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        boolean isLoggedIn = session.getAttribute("LoggedIn") != null;


        if (!isResourceRequest(request)) {

            // Если это защищенная страница и мы не залогинились
            if (isAuthenticationRequired(request) && !isLoggedIn) {
                response.sendRedirect("/Lab3-1/view/index.xhtml");
            // Если это незащищенная станица и мы залогинены
            } else if (!isAuthenticationRequired(request) && isLoggedIn) {
                response.sendRedirect("/Lab3-1/view/main.xhtml");
            } else
                filterChain.doFilter(request, response);

        } else filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public boolean isResourceRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("javax.faces.resource");
    }

    public boolean isAuthenticationRequired(HttpServletRequest request) {
        return !(request.getRequestURI().contains("index") ||
                request.getRequestURI().equals("/Lab3-1") ||
                request.getRequestURI().equals("/Lab3-1/"));
    }
}
