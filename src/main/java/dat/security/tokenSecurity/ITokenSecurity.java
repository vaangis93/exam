package dat.security.tokenSecurity;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import dat.dtos.UserDTO;
import com.nimbusds.jose.JOSEException;
import java.text.ParseException;

public interface ITokenSecurity {
    UserDTO getUserWithRolesFromToken(String var1) throws ParseException;

    boolean tokenIsValid(String var1, String var2) throws ParseException, JOSEException;

    boolean tokenNotExpired(String var1) throws ParseException;

    int timeToExpire(String var1) throws ParseException;

    String createToken(UserDTO var1, String var2, String var3, String var4) throws TokenCreationException;
}

