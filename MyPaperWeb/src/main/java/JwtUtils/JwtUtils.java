package JwtUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.mypaper.www.mapper.WebusersMapper;
import net.mypaper.www.pojo.webusers;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class JwtUtils {
    static final String secreteKey = "p2kd;lcmmee3@ad";
    @Autowired
    static WebusersMapper webusersMapper;

    public static String createToken(String uid){
        Date date = new Date(System.currentTimeMillis()+60 * 60 * 1000);
        Algorithm algorithm = Algorithm.HMAC256(secreteKey);
        return JWT.create().withClaim("uid", uid)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token){
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("token不能为空！");
        }
        Algorithm algorithm = Algorithm.HMAC256(secreteKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String uid = jwt.getClaims().get("uid").asString();
        QueryWrapper<webusers> wrapper =new QueryWrapper<>();
        wrapper.eq("uid", uid);
        if(webusersMapper.selectList(wrapper).isEmpty())
            throw new RuntimeException("用户不存在！");
        Date dateEx =jwt.getExpiresAt();
        Date current = new Date(System.currentTimeMillis());
        if (dateEx.after(current))
            throw new RuntimeException("token过期");
        return true;
    }

    public static String getUidByToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secreteKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("uid").asString();
    }

}
