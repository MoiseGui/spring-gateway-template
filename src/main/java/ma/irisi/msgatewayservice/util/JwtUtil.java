package ma.irisi.msgatewayservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = 234234523523L;
    private String secretKey = "myScret123";


    public boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        return (username != null && !isTokenExpired(token));
    }

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    //for retrieving any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


    //check if the token has expired
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

//    private void addRolesClaim(UserDetails userDetails, Map<String, Object> claims) {
//        if (userDetails == null || userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty()) {
//            return;
//        } else {
//            String roles = "";
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            for (GrantedAuthority granted : authorities) {
//                roles += granted.getAuthority() + ",";
//            }
//            if (!roles.isEmpty()) {
//                roles = roles.substring(0, roles.length() - 1);
//            }
//            claims.put("roles", "[" + roles + "]");
//        }
//    }

}
