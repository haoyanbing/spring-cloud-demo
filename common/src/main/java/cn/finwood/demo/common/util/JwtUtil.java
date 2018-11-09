package cn.finwood.demo.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * created by haoyanbing on 2018/11/9 10:08
 */
public class JwtUtil {
    private final static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    // 密钥
    private final static String SECRET = "123456abc";
    // 过期时间一天
    public static final int CALENDAR_INTERVAL = 1;
    // 签发者
    public static final String ISSUER = "Finwood";
    public static final String SUBJECT = "APP";
    public static final String USER_ID = "userId";

    /**
     * 生成TOKEN
     * @param userId
     * @return
     */
    public static String create(Long userId) {
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, CALENDAR_INTERVAL);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        try {
            String token = JWT.create().withHeader(map)
                    .withIssuer(ISSUER)
                    .withSubject(SUBJECT)
                    .withClaim(USER_ID, null == userId ? null : userId.toString())
                    .withIssuedAt(iatDate)
                    .withExpiresAt(expiresDate)
                    .sign(Algorithm.HMAC256(SECRET));
            return token;
        } catch (Exception e) {
            logger.error("生成token异常", e);
        }
        return null;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static Map<String, Claim> verify(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            logger.error("token检验异常", e);
        }
        return null;
    }

    /**
     * 获取userId
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        Map<String, Claim> claims = verify(token);
        if (claims != null) {
            Claim useIdClaim = claims.get(USER_ID);
            if (null == useIdClaim || StringUtils.isEmpty(useIdClaim.asString())) {
                logger.error("token检验异常");
                return null;
            }
            return Long.valueOf(useIdClaim.asString());
        }
        return null;
    }
}
