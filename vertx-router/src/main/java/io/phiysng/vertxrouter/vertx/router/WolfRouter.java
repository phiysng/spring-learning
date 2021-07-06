package io.phiysng.vertxrouter.vertx.router;

import io.phiysng.vertxrouter.annotation.ZPath;
import io.phiysng.vertxrouter.annotation.ZRoute;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ZRoute(url = "/wolf")
public class WolfRouter implements IRouter {
    @ZPath(url = "/shield")
    public String shield() {
        String msg = "Hello Shield";
        log.debug(msg);
        return msg;
    }

    @ZPath(url = "/loft")
    public String loft() {
        String msg = "Hello Loft";
        log.debug(msg);
        return msg;
    }
}
