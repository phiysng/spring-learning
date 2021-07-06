package io.phiysng.vertxrouter.vertx.component;

import io.phiysng.vertxrouter.annotation.ZPath;
import io.phiysng.vertxrouter.annotation.ZRoute;
import io.phiysng.vertxrouter.vertx.router.IRouter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Command Runner来运行后处理逻辑
 */
@Slf4j
@Component
public class VertxApplicationCommandRunner implements CommandLineRunner {
    @Autowired
    List<IRouter> routerList;

    // 保存url到对应的方法的映射
    private Map<String, Method> urlMap = new HashMap<>();

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
                        urlMap.put(uri, method);
                    }
                }
            }

        }
        for (Map.Entry<String, Method> kv : urlMap.entrySet()) {
            log.info("注册的路由: {} : {}", kv.getKey(), kv.getValue());
        }
    }
}
