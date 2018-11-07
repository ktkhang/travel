package com.major.project.travel.security;

import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.User;
import com.major.project.travel.service.UserService;
import com.major.project.travel.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ktKhang on 31, Oct, 2018
 **/
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            System.out.println("jwt:");
            System.out.println(jwt);
            if (StringUtils.hasText(jwt)) {
                // validate token
                this.tokenProvider.validateToken(jwt);
                String userUid = tokenProvider.getUserUidFromJWT(jwt);
                System.out.println(userUid);

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userUid);
                System.out.println(userDetails);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }catch (RestException e) {
            throw e;
        } catch (Exception ex) {
            throw new RestException("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constant.AUTHORIZATION_STRING);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constant.BEARER_STRING)) {
            return bearerToken.substring(Constant.BEARER_STRING.length(), bearerToken.length());
        }
        return null;
    }
}
