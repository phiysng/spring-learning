package io.phiysng.vertxrouter.vertx.router;

/**
 * 所有路由的基类 为了组件扫描时获得所有的路由
 * 还有一种实现方式是指定一个 `basePackage`
 * 然后扫描这个包及其子目录下所有的带有注解的类 @MyBatis
 */
public interface IRouter {
}
