package cinema.filtrates;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since
 */

public class CinemaFiltrate implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, resp);
    }
}
