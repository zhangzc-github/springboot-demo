# springboot-demo
## 根据尚硅谷Spring Boot教程学习的入门级代码，基于Spring Boot 2.2.5
- 发送delete请求，发生405错误：发现在自动配置类WebMvcAutoConfiguration中
```
@Bean
	@ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
	@ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled", matchIfMissing = false)
	public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new OrderedHiddenHttpMethodFilter();
	}
```
因此添加配置
```
spring.mvc.hiddenmethod.filter.enabled=true
```
