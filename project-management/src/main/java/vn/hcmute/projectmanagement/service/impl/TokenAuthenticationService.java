package vn.hcmute.projectmanagement.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000; // 1 days

    static final String SECRET = "ThisIsASecret";

    static final String TOKEN_PREFIX = "Bearer";

    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username, Authentication auth) {
        Claims claims=Jwts.claims().setSubject(username);
        claims.put("authorities",auth.getAuthorities().stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
        String JWT = Jwts.builder().setSubject(username)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        String token=TOKEN_PREFIX + " " + JWT;
        res.addHeader(HEADER_STRING, token);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = retrieveUsernameByToke(token);
            System.out.println("user getAuthentication "+user);
            System.out.println(getClaimsByToken(token).get("authorities"));
            List<Object> authorities= (List<Object>) getClaimsByToken(token).get("authorities");
//            System.out.println("user getAuthentication "+authorities.get(0));
            Collection<SimpleGrantedAuthority> grantedAuthorities=new HashSet<>();
            authorities.forEach(role->grantedAuthorities.add(new SimpleGrantedAuthority(role.toString().replace("authority=",""))));
            System.out.println("user getAuthentication "+grantedAuthorities.toString());

//            System.out.println("User Details : "+userDetails);
            return user != null ? new UsernamePasswordAuthenticationToken(user,null,grantedAuthorities) : null;
        }
        return null;
    }

    public static Claims getClaimsByToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
    }

    public static String retrieveUsernameByToke(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }
}
