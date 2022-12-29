package markdownblog.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Implements Single Page Application logic for dealing with navigation
 * using the HTML5 History API.
 * <p>
 * Inspiration: https://github.com/bripkens/connect-history-api-fallback/
 */
public class HistoryApiFallbackFilter extends HttpFilter {

    private static final String REENTRANCY_KEY = HistoryApiFallbackFilter.class.getName();

    protected static String FORWARD_PATH_CONFIG_PARAMETER = "forwardPath";
    protected static String forwardPath = null;

    /**
     *
     */
    private boolean isGet(String method) {
        return method.equals("GET");
    }

    /**
     *
     */
    private boolean hasHeader(String header) {
        return header != null && header.length() > 0;
    }

    /**
     *
     */
    private boolean isApplicationJson(String header) {
        return header.contains("application/json");
    }

    /**
     *
     */
    private boolean acceptsHtml(String header) {
        return header.contains("text/html") || header.contains("*/*");
    }

    /**
     *
     */
    private boolean pathIncludesDot(String path) {
        return path != null && path.indexOf('.') != -1;
    }

    /**
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("through doFilter");

        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);

        String method = request.getMethod().toUpperCase();
        String accept = request.getHeader("Accept");
        if (accept != null)
            accept = accept.toLowerCase();
        String requestURI = request.getRequestURI();

        System.out.println(requestURI.contains("/api/"));

        Object reentrancyKey = request.getAttribute(REENTRANCY_KEY);

        boolean doFilter = false;
        if (reentrancyKey != null ||
                !requestURI.contains("/api/") ||
                !isGet(method) ||
                !hasHeader(accept) ||
                isApplicationJson(accept) ||
                !acceptsHtml(accept) ||
                pathIncludesDot(requestURI)) {

            doFilter = true;
        }

        if (doFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // Prevent the next request from hitting this filter
            request.setAttribute(REENTRANCY_KEY, Boolean.TRUE);
            request.getRequestDispatcher(forwardPath).forward(request, response);
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {
        // ignore
    }

    /**
     *
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        forwardPath = filterConfig.getInitParameter(FORWARD_PATH_CONFIG_PARAMETER);
        System.out.println("through init");
        if (forwardPath == null) {
            throw new ServletException("Please set the '" + FORWARD_PATH_CONFIG_PARAMETER + "' servlet filter config as part of the " + REENTRANCY_KEY);
        }
    }
}