package io.phiysng.vertxrouter.vertx.router;

import io.phiysng.vertxrouter.annotation.ZPath;
import io.phiysng.vertxrouter.annotation.ZRoute;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ZRoute(url = "/wolf")
public class WolfRouter implements IRouter {
    @ZPath(url = "/shield")
    public void shield() {
        log.debug("Hello Shield");
    }

    @ZPath(url = "/loft")
    public void loft() {
        log.debug("Hello Loft");
    }
}
