#Urule规则引擎使用

[TOC]

###[Urule](http://www.bstek.com/products/urule)简介
>嵌在业务系统当中的一种组件,通过预定义的语义规范来编写业务规则，接收输入的业务数据，根据业务规则做出相应决策.可根据业务系统的变化**实时任意**在线修改业务规则而不需要改变后端服务器实现代码.

>Urule底层基于Spring和Hibernate.

    

###使用场景
举例说明:用户消费积分
![](http://wiki.bsdn.org/download/attachments/71532859/F9F5F2CD-ACE9-4026-8C34-D7C8A4844227.png?version=1&modificationDate=1434355537000&api=v2&effects=border-simple,shadow-kn)
积分规则可能随时会做更改,一般做法是每次修改积分获取规则.都需要重新写一个函数方法来计算,并且这个方法只能在当前规则下使用.考虑还要重新部署项目.相当费时费力.

使用规则引擎则可以将这一部分独立出来.配置好相关的参数.后台只需要提供基本的POJO对象,和一般的逻辑方法(比如说判断是否是生日这种[动作库方法](http://wiki.bsdn.org/pages/viewpage.action?pageId=69828714)).然后在前端页面定义相应积分获取计算规则即可.

#Urule讲解使用
以小轿车的定价规则为例进行讲解说明
![](http://7xor08.com1.z0.glb.clouddn.com/bmkp_2016.7.11.png)
###1.[创建dorado7项目工程](http://wiki.bsdn.org/pages/viewpage.action?pageId=8356049)
官方推荐适应他们集成的Eclipse-dorado IDE(仅适用于windows),且JDK版本1.6.
导入IDEA可能会报错,所以我是创建一个maven工程,把他们那些配置文件直接拉过来的.


###2.配置开发
Github:[urule-bmkp](https://github.com/jiang7462582/urule-bmkp)

####项目结构
![](http://7xor08.com1.z0.glb.clouddn.com/urlue-bmkp-struct.png)

最重要的是需要提前在代码中配置规则和数据对应关系
```java
	 public Double userRuleTest() throws Exception {
        //从Spring中获取KnowledgeService接口实例
        KnowledgeService knowledgeService = (KnowledgeService) Utils.getApplicationContext()
                .getBean(KnowledgeService.BEAN_ID);
        //通过KnowledgeService接口获取资源包usercost
        KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("bmkp/usercost");
        //KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("rs-exp");
        //通过取到的KnowledgePackage对象创建KnowledgeSession对象
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);
        Map<String, Object> params = new HashMap<String, Object>();
        //通过参数的方式设置获取经验的方式为购买经验
        params.put("userRuleCost", "自定义用户消费金额");
        User user = new User();
        user.setDistance(7.0);
        user.setWaitTime(30L);
        user.setCost(0.0);
        session.insert(user);
        session.fireRules(params);
        return user.getCost();
    }
```

此处我是new的一个User对象,在实际接口中User()中的对象内容(`distance`,`waitTime`都应该是传进去的)
上面代码中的`bmkp/usercost`对应的就是待会在前端页面编辑的页面规则.

Test.jsp文件
```html
<%@page import="com.bmkp.urule.demo.test.TestURule"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
       This is for urule-bmkp
	测试结果：<%=new TestURule().test()%>
</body>
</html>
```

####前端规则配置
访问地址:(App-Name直接换成你的war包名)
```http
http://localhost:8080/{App-Name}/com.bstek.urule.repo.view.Repository.d
```
1. 导入相关的用户类对象[变量库](http://wiki.bsdn.org/pages/viewpage.action?pageId=69828686)
![](http://7xor08.com1.z0.glb.clouddn.com/urule-bmkp-bianliang.png)
2. 导入[常量库](http://wiki.bsdn.org/pages/viewpage.action?pageId=69828690)
![](http://7xor08.com1.z0.glb.clouddn.com/urule-bmkp-changliang.png)
3.导入[动作操作](http://wiki.bsdn.org/pages/viewpage.action?pageId=69828714)
![](http://7xor08.com1.z0.glb.clouddn.com/urule-bmkp-action.png)
*注意*:此处的Bean ID 需要和spring配置文件一致
4.编写执行脚本(暂时我只会脚本式)
![](http://7xor08.com1.z0.glb.clouddn.com/urule-bmkp-jiaoben.png)
5.测试并发布运行脚本
 双击资源包新建待发布的脚本
![](http://7xor08.com1.z0.glb.clouddn.com/urule-bmkp-fabu.png) 
此处的编码id必须和你的Java代码资源包必须一致
```java
//通过KnowledgeService接口获取资源包usercost
KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("bmkp/usercost");
```
测试(检测脚本语法逻辑错误)完成后发布即可.
访问:http://localhost:8080/urule-bmkp-1.0/UserRuleTest.jsp 即可看到
最终结果: 16.5
8+(7-3)*1.5+(30-5)*0.1 = 16.5

如果需要重新制定规则,修改规则的脚本文件,重新发布完成后就可以.

#待补充内容
1. ~~每次java项目有其它代码更新,重启tomcat,规则配置文件会丢失.暂时可行方法是部署前把配置文件导出来,重启之后需要再次导入.是不是有其它解决方法~~
2. 权限控制模块
3. 每次接口都条用接口方法,是否影响性能





