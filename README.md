## 介绍
各种即时通信技术实践,服务端Java实现。
Spring4.0引入spring-websocket支持WebSocket

- Ajax长轮询
- Ajax轮询
- iframe长轮询
- server-sent-events
- websoket

![](/websocket-chat.jpg)

## 客户端
在client文件下启动静态文件服务器。例如,使用[anywhere](https://github.com/JacksonTian/anywhere)

```
anywhere -h localhost -p 8000
```


## 服务端
Java + SpringMVC实现
IntelliJ IDEA 本地启动tomcat


## 注意
在本地启动两个服务器，client域名必须是：http:localhost:8000	
因为server本地启动是127.0.0.1:80，允许跨域访问的origin在server设置了http:localhost:8000