# Java-CoolQSDK

## 依赖http-api,详见如下:**[链接](https://github.com/richardchien/coolq-http-api)**

------

# 配置SDK

初始化SDK后,会输出该配置文件new File("CoolQSDK.properties");

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

## 1.初始化SDK

```java
SDKInit.init(map);
//如果你的项目没使用sdk.util.bean.BeanUtils工具类,直接传入null
//否则根据以下描述自己构造相应的map
//初始化方法需要传入一个Map<Class,String>,map的所有key-value会做如下处理
//String path = key.getProtectionDomain().getCodeSource().getLocation().getFile()
//如果new File(path)是文件夹,则会用Class.forName()加载其下所有类名以value开头的类
//如果new File(path)是jar文件,则会扫描jar里所有类名以value开头的类,并用Class.forName()加载
//注:没有被SDK加载的类,用BeanUtils处理的话,会抛出异常
```

## 2.注册监听器

```java
//首先实现CoolQListener
public class MyListener implements CoolQListener {
  //监听器用CoolQEventHandler注解
  //ignoreCancelled和priority均可省,用处见CoolQEventHandler.java
  //以GroupMessageEvent为例
  @CoolQEventHandler(ignoreCancelled和 = true,priority = NORMAL)
  public void myFirstListener(GroupMessageEvent event){
    /*
    your code
    */
    //注:对于可回复的事件,如群消息,加好友请求,加群请求等,若对事件做了了回复
    //如event.replyMessage("reply",true),则会自动调用event.setCancelled(true)方法
  }
  //可回复的事件位于sdk.event.syncro包下
  //不可回复的事件位于sdk.event.asynchro包下
}

//以下是一个完整的初始化例子
package com.project;
public class YourProjectClass{
  public static void main(String[] args){
    Map<Class,String> map = new HashMap();
    map.put(YourProjectClass.class,"com.project.");
    SDKInit.init(map);//初始化SDK
    CoolQEventManager.getInstance().registerEvents(new MyListener());//注册监听器
  }
}
```

## 3.调用CoolQAPI

所有可用API由sdk.CoolQAPI类的静态方法提供