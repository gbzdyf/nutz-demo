package net.wendal.nutzbook.mvc;

import org.nutz.mvc.NutFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by y on 2016/10/13.
 */
public class NutzBookNutFilter extends NutFilter {

    protected Set<String> prefixs = new HashSet<String>();


    public void init(FilterConfig conf) throws ServletException {
        super.init(conf);
        //含义是, 如果访问/druid或/rs下的路径,就无条件跳过NutFilter
        //然后,打开web.xml, 将其中的org.nutz.mvc.NutFilter改成net.wendal.nutzbook.mvc.NutzBookNutFilter
        prefixs.add(conf.getServletContext().getContextPath() + "/druid/");
        prefixs.add(conf.getServletContext().getContextPath() + "/rs/");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) req).getRequestURI();
            for (String prefix : prefixs) {
                if (uri.startsWith(prefix)) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        super.doFilter(req, resp, chain);
    }
}
