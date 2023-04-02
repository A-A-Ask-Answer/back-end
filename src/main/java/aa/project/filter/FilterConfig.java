package aa.project.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FilterConfig {
/*implements Filter {
    private final AccountRepository accountRepository;

    public FilterConfig(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        log.info("admin에 대한 Filter start");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        String servletPath = request1.getServletPath();
        log.info("serevl = {}", servletPath);

    }

    @Override
    public void destroy() {
        log.info("admin에 대한 Filter end");
    }*/
}
