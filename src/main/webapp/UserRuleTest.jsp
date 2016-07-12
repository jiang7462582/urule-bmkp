<%--
  Created by IntelliJ IDEA.
  User: jiang
  Date: 16/7/12
  Time: 上午9:44
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.bmkp.urule.demo.test.TestURule"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserRuleTest</title>
</head>
<body>
    自定义：<%=new TestURule().userRuleTest()%>
</body>
</html>
