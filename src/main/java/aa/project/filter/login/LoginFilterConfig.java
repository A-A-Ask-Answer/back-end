package aa.project.filter.login;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class LoginFilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("LoginFilterConfig");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getMethod().equals("POST") || req.getMethod().equals("DELETE")) {
            if (req.getSession(false) == null) {
                res.sendError(403, "로그인하세요.");
                return;
            }
        }
        if (req.getSession() != null) {
            chain.doFilter(req, res);
        }
    }


    @Bean
    public FilterRegistrationBean<LoginFilterConfig> loginFilter() {
        FilterRegistrationBean<LoginFilterConfig> filter = new FilterRegistrationBean<>();
        filter.setFilter(new LoginFilterConfig());
        filter.addUrlPatterns("/api/ask/*", "/api/room/*");
        filter.setOrder(1);
        return filter;
    }
}
