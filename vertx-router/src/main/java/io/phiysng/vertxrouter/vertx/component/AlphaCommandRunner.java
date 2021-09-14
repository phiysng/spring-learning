package io.phiysng.vertxrouter.vertx.component;

import io.phiysng.vertxrouter.annotation.ZPath;
import io.phiysng.vertxrouter.annotation.ZRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.BiConsumer;

@Slf4j
@Component
public class AlphaCommandRunner implements CommandLineRunner {
    /**
     * https://stackoverflow.com/questions/21344386/getting-application-context-from-a-commandlinerrunner
     */
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        /*
          https://stackoverflow.com/questions/14236424/how-can-i-find-all-beans-with-the-custom-annotation-foo
         */
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ZRoute.class);
        beansWithAnnotation.forEach((s, o) -> log.info("key : {} value : {}", s, o));
    }
}
