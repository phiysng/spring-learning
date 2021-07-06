package io.phiysng.vertxrouter.vertx.component;

import io.phiysng.vertxrouter.annotation.ZPath;
import io.phiysng.vertxrouter.annotation.ZRoute;
import io.phiysng.vertxrouter.vertx.router.IRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Command Runner来运行后处理逻辑
 */
@Slf4j
@Component
public class VertxApplicationCommandRunner extends AbstractVerticle implements CommandLineRunner {
    @Autowired
    List<IRouter> routerList;

    // 保存url到对应的方法的映射
    private Map<String, Tuple2<Method, IRouter>> urlMap = new HashMap<>();

    /**
     * 解析并注册所有的路由和方法
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        for (IRouter router : routerList) {
            Class<?> clazz = router.getClass();
            if (clazz.isAnnotationPresent(ZRoute.class)) {
                // 获取所有的带有@ZPath的Method
                ZRoute zRoute = clazz.getAnnotation(ZRoute.class);
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(ZPath.class)) {
                        ZPath zPath = method.getAnnotation(ZPath.class);
//                        log.debug("注册的路由:{}{}", zRoute.url(), zPath.url());
                        String uri = String.format("%s%s", zRoute.url(), zPath.url());
                        if (urlMap.containsKey(uri)) {
                            log.warn("key {} exists already , will override existing value {}", uri, urlMap.get(uri));
                        }
                        urlMap.put(uri, Tuples.of(method, router));
                    }
                }
            }

        }
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        for (Map.Entry<String, Tuple2<Method, IRouter>> kv : urlMap.entrySet()) {
            Route route = router.route().path(kv.getKey());
            log.info("注册到vertx: {} : {} {}", kv.getKey(), kv.getValue().getT2(), kv.getValue().getT1());

            route.handler(ctx -> {
                Method method = kv.getValue().getT1();
                IRouter iRouter = kv.getValue().getT2();
                String msg = null;
                try {
                    msg = (String) method.invoke(iRouter);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                HttpServerResponse response = ctx.response();
                response.putHeader("content-type", "text/plain");
                // Write to the response and end it
                Logger logger = LoggerFactory.getLogger(this.getClass());
                logger.info("{} : response message : {}", ctx.request().path(), msg);
                response.end(msg);
            });
        }
        // listen
        server.requestHandler(router).listen(8080);
    }
}
