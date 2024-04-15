## 项目部署
本项目默认windows环境, 默认端口8080, 运行需确保8080端口没有被占用, 需配置本地默认账户的redis, jdk17, 并确保网络正常, 没有使用远程代理等, 管理端默认账户为admin, 密码123
若需在linux环境部署，还需要修改以下几个地方：
### A
1. org.knight.infrastructure.fisco.config.BcosConfig第33行,"conf"修改linux环境下的绝对路径 或 修改
2. 拷贝infrastructure/src/main/resources/conf目录至linux系统指定目录下
### B
或运行jar包时, 指定以下配置文件 application-server.yml
可以根据需要修改配置文件中的配置项

## 项目在线接口文档
以edge\chrome\火狐浏览器, 访问以下网址:
http://43.143.86.190:8080/doc.html#/home



