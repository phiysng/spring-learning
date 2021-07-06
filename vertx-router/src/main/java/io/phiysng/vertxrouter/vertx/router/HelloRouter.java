package io.phiysng.vertxrouter.vertx.router;

import io.phiysng.vertxrouter.annotation.ZPath;
import io.phiysng.vertxrouter.annotation.ZRoute;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ZRoute(url = "/hello")
public class HelloRouter implements IRouter {
    @ZPath(url = "/world")
    public String helloWorld() {
        String msg = "Hello World";
        log.debug(msg);
        return msg;
    }

    @ZPath(url = "/foo")
    public String helloFoo() {
        String msg = "Hello Foo";
        log.debug(msg);
        return msg;
    }

    @ZPath(url = "/bar")
    public String helloBar() {
        String msg = "Hello Bar";
        log.debug(msg);
        return msg;
    }
}
