package com.ApoorvMathur.BurgerBackend.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class TokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getRequestURL().toString());
        String requestUrl = "http://localhost:5000";
        if(request.getRequestURL().toString().equals(requestUrl + "/ingredients/get")){
             chain.doFilter(request, response);
        }
        final String method = request.getHeader("Method");
        final String requestTokenHeader = request.getHeader("Authorization");
        String token;
        if (requestTokenHeader != null && method != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            try {
                if(method.equals("GOOGLE")){
                    String userId = getGoogleTokenInfo(token);
                    if(userId != null){
                        request.setAttribute("userId", userId);
                        chain.doFilter(request, response);
                    }
                    else{
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    }
                }
                else if(method.equals("FACEBOOK")){
                    String userId = getFacebookTokenInfo(token);
                    if(userId != null){
                        request.setAttribute("userId", userId);
                        chain.doFilter(request, response);
                    }
                    else{
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    }
                }
                else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("Unable to get JWT Token");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    public String getGoogleTokenInfo(String token) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<?, ?> googleResponse;
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("https://www.googleapis.com/oauth2/v3/tokeninfo")
                    .queryParam("id_token", token);
            googleResponse = new ObjectMapper().readValue(restTemplate.getForObject(uriBuilder.toUriString(),String.class), Map.class);

        } catch (HttpClientErrorException e) {
            return null;
        }
        return (String) googleResponse.get("sub");
    }

    public String getFacebookTokenInfo(String token) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<?, ?> facebookResponse;
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("https://graph.facebook.com/oauth/access_token")
                    .queryParam("client_id", "645535929705582").queryParam("client_secret","95db257ec815f87cb7b51b67b35b2da2")
                    .queryParam("grant_type","client_credentials");
            facebookResponse = new ObjectMapper().readValue(restTemplate.getForObject(uriBuilder.toUriString(),String.class), Map.class);
            String accessToken = (String)facebookResponse.get("access_token");
            uriBuilder = UriComponentsBuilder.fromUriString("https://graph.facebook.com/debug_token")
                    .queryParam("input_token", token).queryParam("access_token", accessToken);
            Map<?, ?> response = restTemplate.getForObject(uriBuilder.toUriString(),Map.class);
            assert response != null;
            Map<?, ?> data = (Map<?, ?>) response.get("data");
            if((Boolean) data.get("is_valid")){
                return (String) data.get("user_id");
            }
            return null;

        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
