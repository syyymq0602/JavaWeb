# JavaWeb
使用Tomcat 8.5.7搭建**用户信息管理系统**

### 基本配置
JDK:11<br>
Tomcat:8.5.70(10以上会报错)<br>
libs:
 + commons-beanutils-1.8.0.jar
 + commons-logging-1.2.jar
 + druid-1.0.9.jar
 + javax.servlet.jsp.jstl.jar
 + jstl-impl.jar
 + mchange-commons-java-0.2.12.jar
 + mysql-connector-java-5.1.37-bin.jar
 + spring-beans-5.0.0.RELEASE.jar
 + spring-core-5.0.0.RELEASE.jar
 + spring-jdbc-5.0.0.RELEASE.jar
 + spring-tx-5.0.0.RELEASE.jar<br>
<p>注：HTML文件可以删掉</p>
<hr>


### 踩坑
1. 使用IDEA部署项目到Tomcat服务器上不能访问静态资源(bootstrap/jquery等)
 **解决**：原因是即使开启了tomcat服务，但是并没有设置tomcat将这些css,js文件映射出去，出于文件保护机制，导致无法访问和加载这些文件，需要在IDEA中将静态文件映射出去。在Tomcat配置**Deployment**下的**Deploy at the server startup**通过**+**，选择**External Source**将对应的静态文件选中，在最下方**Application context**中加上**虚拟路径**
2. 第三方包(如sweetalert2.min.js、toastr.min.js等)若不起作用则需要手动将包复制到**target**对应静态文件夹目录下。
