# Java-CoolQSDK

## 依赖http-api,详见如下:**[链接](https://github.com/richardchien/coolq-http-api)**

## 基于Java-CoolQSDK开发的  [例子](https://github.com/azbh111/CoolQExampleJavaPlugin)  可以直接在其基础上进行开发

------

# 对接http-api

初始化SDK后,配置文件会输出到该位置:new File("CoolQSDK.properties").getPath()

## CoolQSDK.properties

```
#http-api的api接口调用地址,详见http-api
coolQURL=http://127.0.0.1:60778/
#SDK监听地址,用于接收http-api的事件上报
SDKHost=127.0.0.1
#SDK监听地址,用于接收http-api的事件上报
SDKPort=55555
```

配置完成后重启

# 使用说明

具体使用方法可以参照 [开发样例](https://github.com/azbh111/CoolQExampleJavaPlugin) 或直接在其基础上进行开发