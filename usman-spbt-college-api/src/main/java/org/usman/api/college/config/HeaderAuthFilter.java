package org.usman.api.college.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.usman.api.college.common.response.IntrospectResponse;
import org.usman.api.college.services.KeycloakTokenService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class HeaderAuthFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "X-Auth-Key";
    private static final String SECRET_KEY = "mysecretkey";

    private static final ObjectMapper objectMapper = new ObjectMapper();

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

        KeycloakTokenService tokenService = new KeycloakTokenService();
        String requestURI = request.getRequestURI();
        log.info("requestURI :{}", requestURI);
        // Skip auth for Swagger, login, docs, etc.
        if (WHITELIST.stream().anyMatch(requestURI::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        //for keycloak access token validation
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            log.info("Token from header: {}", token);
            // Optionally decode or introspect token here
            IntrospectResponse introspect = tokenService.introspect(token);
            log.info("Token introspect: {}", introspect.getActive());
            if (introspect.getActive()) {
                printTokenInfo(token);
            } else {
                log.warn("*** Inactive or invalid token...");
            }
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

    /**
     * Print all possible info for the given access token.
     * @param token
     */
    public void printTokenInfo(String token) {
        try {
            // Decode JWT without verifying (for display purposes)
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                log.warn("Invalid token format");
                return;
            }

            // Decode payload part (index 1)
            String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
            Map<String, Object> payload = objectMapper.readValue(payloadJson, Map.class);

            log.info("==== TOKEN INFORMATION ====");
            log.info("Subject (sub): {}", payload.get("sub"));
            log.info("Username: {}", payload.get("preferred_username"));
            log.info("Client ID (azp): {}", payload.get("azp"));
            log.info("Issued At (iat): {}", payload.get("iat"));
            log.info("Expiry (exp): {}", payload.get("exp"));
            log.info("Email: {}", payload.get("email"));
            log.info("Name: {}", payload.get("name"));

            // Realm roles
            Map<String, Object> realmAccess = (Map<String, Object>) payload.get("realm_access");
            if (realmAccess != null && realmAccess.get("roles") != null) {
                log.info("Realm Roles: {}", realmAccess.get("roles"));
            }

            // Client roles
            Map<String, Object> resourceAccess = (Map<String, Object>) payload.get("resource_access");
            if (resourceAccess != null) {
                for (String client : resourceAccess.keySet()) {
                    Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get(client);
                    log.info("Client: {}, Roles: {}", client, clientRoles.get("roles"));
                }
            }

            log.info("Scope: {}", payload.get("scope"));
            log.info("===========================");

        } catch (Exception e) {
            log.error("Error parsing token: {}", e.getMessage(), e);
        }
    }
}
