swagger生成spring-boot server端

1. 定义swagger文件yaml或者json

2. pom引入plugin
   ```
   <groupId>io.swagger</groupId>
   <artifactId>swagger-codegen-maven-plugin</artifactId>
   ```
   see pom.xml for details
   * 在这个plugin中可以定义base，api，model的package
   * 定义服务器端采用什么技术，如spring-boot
   * 定义是否采用代理方式，如果不用代理方式则需要在Configuration初始化一个Api Bean
3. 实现Application
   ```
   @SpringBootApplication
   ```                

4. 通过代理方式实现Api
    ```
    @Service
    public class ProductApiDelegateImpl implements ProductApiDelegate
    ```
