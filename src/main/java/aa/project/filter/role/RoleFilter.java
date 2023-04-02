package aa.project.filter.role;

import aa.project.account.entity.Account;
import aa.project.account.entity.type.Role;
import aa.project.account.repository.AccountRepository;
import aa.project.exception.account.AccountErrorCode;
import aa.project.exception.account.AccountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class RoleFilter implements Filter {
    private final AccountRepository accountRepository;


    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("RoleFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse res = (HttpServletResponse) response;

        if (request.getSession(false) == null) {
            res.sendError(401, "로그인");
        } else {
            String loginId = (String) request.getSession().getAttribute("ID");
            if (loginId == null) {
                res.sendError(401, "관리자 아님");
                return;
            }
            Account account = accountRepository.findByLoginId(String.valueOf(request.getSession().getAttribute("ID"))).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ACCOUNT));
            if (account.getRole() != Role.ADMIN) {
                res.sendError(401, "관리자 아님");
                return;
            }

            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Bean
    public FilterRegistrationBean<RoleFilter> filter() {
        FilterRegistrationBean<RoleFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new RoleFilter(accountRepository));
        filter.addUrlPatterns("/api/admin/*");
        filter.setOrder(2);
        return filter;
    }
}
