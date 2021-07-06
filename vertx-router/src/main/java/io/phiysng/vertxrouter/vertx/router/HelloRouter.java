package io.phiysng.vertxrouter.vertx.router;

import io.phiysng.vertxrouter.annotation.ZPath;
import io.phiysng.vertxrouter.annotation.ZRoute;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ZRoute(url = "/hello")
public class HelloRouter implements IRouter {
    @ZPath(url = "/world")
    public void helloWorld() {
        log.debug("Hello World");
    }

    @ZPath(url = "/foo")
    public void helloFoo() {
        log.debug("Hello Foo");
    }

    @ZPath(url = "/bar")
    public void helloBar() {
        log.debug("Hello Bar");
    }
}
