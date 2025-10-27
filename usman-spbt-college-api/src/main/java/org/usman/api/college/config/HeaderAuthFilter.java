package org.usman.api.college.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class HeaderAuthFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "X-Auth-Key";
    private static final String SECRET_KEY = "mysecretkey";

    private static final List<String> WHITELIST = List.of(
            "/api/login",
            "/v1/login/validate",
            "/swagger-ui",
            "/v3/api-docs",
            "/favicon.ico",
            "/error"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        log.info("requestURI :{}", requestURI);
        // Skip auth for Swagger, login, docs, etc.
        if (WHITELIST.stream().anyMatch(requestURI::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        String key = request.getHeader(AUTH_HEADER);
        log.info("key :{}", key);
        if (SECRET_KEY.equals(key)) {
            log.info(" *** header auth success");
            Authentication auth = new PreAuthenticatedAuthenticationToken("user", null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } else {
            log.info(" ** auth failed");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Please check the URI and headers");
        }
    }
}
