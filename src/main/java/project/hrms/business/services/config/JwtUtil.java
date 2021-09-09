package project.hrms.business.services.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    // hash işlemi yaparken kullanılacak key 
    private String SECRET_KEY = "javaapplication";
    private long expirationTime = 600000;

    // verilen token a ait kullanıcı adını döndürür.
    public String extractUsername(String token) {
    	System.out.println(extractClaim(token, Claims::getSubject));
        return extractClaim(token, Claims::getSubject);
    }

    // verilen token a ait token bitiş süresini verir.
    public Date extractExpiration(String token) {
    	System.out.println(Claims.EXPIRATION);
    	System.out.println(extractClaim(token, Claims::getExpiration));
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        System.out.println(claims);
        return claimsResolver.apply(claims);
    }

    // verilen token a ait claims bilgisini alır.
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().setExpiration(new Date( System.currentTimeMillis() + expirationTime));
    }

    // token ın geçerlilik süre doldu mu?
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // userDetails objesini alır. createToken metoduna gönderir.
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject) // ilgili kullanıcı
                .setIssuedAt(new Date(System.currentTimeMillis())) // başlangıç
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // bitiş
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // kullanılan algoritma ve bu algoritma çalışırken kullanılacak hash key değeri
                .compact();
    }

    // token hala geçerli mi? kullanıcı adı doğru ise ve token ın geçerlilik süresi devam ediyorsa true döner.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}