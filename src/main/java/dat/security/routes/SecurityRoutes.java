package dat.security.routes;

import com.fasterxml.jackson.databind.ObjectMapper;

import dat.utils.Utils;
import dat.security.controllers.SecurityController;
import dat.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;


public class SecurityRoutes {
    private static ObjectMapper jsonMapper = new Utils().getObjectMapper();
    private static SecurityController securityController = SecurityController.getInstance();
    public static EndpointGroup getSecurityRoutes() {
        return ()->{
            path("/auth", ()->{
                get("/healthcheck", securityController::healthCheck);
                get("/test", ctx->ctx.json(jsonMapper.createObjectNode().put("msg",  "Hello from Open")), Role.REGULAR);
                post("/login", securityController.login(), Role.REGULAR);
                post("/register", securityController.register(), Role.REGULAR);
                post("/user/addrole", securityController.addRole(), Role.REGULAR);
            });
        };
    }
    public static EndpointGroup getSecuredRoutes(){
        return ()->{
            path("/protected", ()->{
                get("/admin_demo", (ctx)->ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from ADMIN Protected")), Role.ADMIN);
            });
        };
    }
}
