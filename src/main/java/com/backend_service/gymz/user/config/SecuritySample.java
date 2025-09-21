package com.backend_service.gymz.user.config;
// package com.backend_service.gymz.common.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.http.SessionCreationPolicy;

// @Configuration
// @EnableWebSecurity
// public class SecuritySample {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         // 1. Quản lý SecurityContext (Bắt buộc, Spring tự xử lý)
//         // -> SecurityContextPersistenceFilter

//         // 2. Channel Security (Tùy chọn, có thể bỏ qua nếu không cần HTTPS)
//         // -> ChannelProcessingFilter
//         // http.requiresChannel(channel -> channel.anyRequest().requiresSecure());

//         // 3. Header Management (Tùy chọn, Spring tự xử lý nếu không custom)
//         // -> HeaderWriterFilter
//         // http.headers(headers -> headers
//         //     .frameOptions(frameOptions -> frameOptions.sameOrigin())
//         //     .xssProtection(xss -> xss.block(true))
//         // );

//         // 4. CSRF Protection (Tùy chọn, API REST thường bỏ qua)
//         // -> CsrfFilter
//         http.csrf(csrf -> csrf.disable()); // Nếu dùng JWT, nên disable

//         // 5. Logout (Tùy chọn, Spring tự xử lý nếu không custom)
//         // -> LogoutFilter
//         http.logout(logout -> logout
//             .logoutUrl("/logout")
//             .logoutSuccessUrl("/login?logout")
//         );

//         // 6. Authentication (Bắt buộc, phải chọn 1 loại xác thực)
//         // -> [UsernamePasswordAuthenticationFilter | BearerTokenAuthenticationFilter | ...]
//         // Option 1: JWT xác thực cho REST API
//         http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Không dùng session
//         http.oauth2ResourceServer(oauth2 -> oauth2.jwt()); // Nếu dùng JWT

//         // Option 2: Form login (bỏ comment nếu muốn dùng session-based)
//         // http.formLogin(form -> form
//         //     .loginPage("/login")
//         //     .permitAll()
//         // );

//         // 7. Remember Me (Tùy chọn, Spring tự xử lý nếu không cấu hình)
//         // -> RememberMeAuthenticationFilter
//         // http.rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret"));

//         // 8. Anonymous (Bắt buộc, Spring tự xử lý)
//         // -> AnonymousAuthenticationFilter

//         // 9. Session Management (Tùy chọn, Spring tự xử lý nếu không cấu hình)
//         // -> SessionManagementFilter
//         // Nếu dùng JWT thì đã cấu hình ở trên là STATELESS

//         // 10. Request Cache (Tùy chọn, Spring tự xử lý)
//         // -> RequestCacheAwareFilter

//         // 11. Exception Handling (Bắt buộc, Spring tự xử lý)
//         // -> ExceptionTranslationFilter
//         http.exceptionHandling(exception -> exception
//             .authenticationEntryPoint((req, res, ex) -> res.sendError(401, "Unauthorized"))
//             .accessDeniedHandler((req, res, ex) -> res.sendError(403, "Forbidden"))
//         );

//         // 12. Authorization (Bắt buộc, phải cấu hình rule hoặc annotation)
//         // -> FilterSecurityInterceptor
//         http.authorizeHttpRequests(auth -> auth
//             .requestMatchers("/user/me").hasAnyAuthority("USER:READ:SELF", "USER:READ:ALL")
//             .requestMatchers("/user/list").hasAuthority("USER:READ:ALL")
//             .requestMatchers("/user").hasAnyAuthority("USER:CREATE:ALL", "USER:CREATE:SELF")
//             .anyRequest().authenticated()
//         );

//         // 13. Web Async Integration (Tùy chọn, Spring tự xử lý)
//         // -> WebAsyncManagerIntegrationFilter

//         // 14. Custom Filter (Tùy chọn, thêm nếu cần logic riêng)
//         // Ví dụ: http.addFilterBefore(new CustomJwtFilter(), UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }