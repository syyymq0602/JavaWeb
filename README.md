# JavaWeb
使用Tomcat 8.5.7搭建**用户信息管理系统**

### 踩坑
1. 使用IDEA部署项目到Tomcat服务器上不能访问静态资源(bootstrap/jquery等)
 **解决**：原因是即使开启了tomcat服务，但是并没有设置tomcat将这些css,js文件映射出去，出于文件保护机制，导致无法访问和加载这些文件，需要在IDEA中将静态文件映射出去。在Tomcat配置**Deployment**下的**Deploy at the server startup**通过**+**，选择**External Source**将对应的静态文件选中，在最下方**Application context**中加上**虚拟路径**

