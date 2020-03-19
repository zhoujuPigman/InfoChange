package cn.huanji.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成jwt token并管理
 * @author 猪肉佬
 */
@Component
public class JWTTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTTokenUtil.class);

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secret ;

    /**
     * 超期限时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 负责生产jwt token
     * @param claims 负载
     * @return 生产token
     */
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 生成token过期时间
     * @return 返回过期时间
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取负载
     * @param token token
     * @return 负载
     */
    public Claims getClaimsFormToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOGGER.info("Jwt格式验证失败:{}",token);
        }

        return claims;
    }

    /**
     * 从token中获取用户名
     * @param token token
     * @return 用户名
     */
    public String getUsernameFromToken(String token){
        String username ;
        try{
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token 客户端传入的token
     * @param userDetails 从数据库查询的用户信息
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        //判断用户名与数据库中是否一致,并且token未过期
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判读token是否已经过期
     * @param token 客户端传入token
     * @return
     */
    private boolean isTokenExpired(String token){
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token){
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生产token
     * @param userDetails 用户信息
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
}
