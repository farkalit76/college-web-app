/*
 * Copyright (c)
 *
 * Copyright (c) 2020, 2025, Takamol and its affiliates. All rights reserved.
 * Use is subject to license terms.
 */

package org.usman.api.college.config;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 06-03-2024
 * @since 1.0
 */
@Slf4j
@Component
@EqualsAndHashCode(callSuper = false)
public class Slf4jMDCFilter {//} extends OncePerRequestFilter {

//    public static final String MDC_UUID_TOKEN_KEY = "id";//"app-traceId";
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        log.info("JwtAuthenticationFilter doFilterInternal ...");
//
//        try {
//            MDC.put(MDC_UUID_TOKEN_KEY, UUID.randomUUID().toString());
//        } catch (Exception ex) {
//            log.error("Exception occurred in filter while setting UUID for logs", ex);
//        } finally {
//            MDC.remove(MDC_UUID_TOKEN_KEY);
//        }
//    }
}
