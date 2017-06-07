package chang.backend.filter;

import chang.backend.constant.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chang on 2017/3/18.
 */
public class CrossDomainFilter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        resp.setHeader("Access-Control-Allow-Origin", Constants.CROSS_ORIGIN);
        resp.setHeader("Access-Control-Allow-Credentials","true");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(200);
            resp.flushBuffer();
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}
