<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background-color:BBCCDD">
  <div style="height: 50px;"></div>
  <div align="center" >
  <form action="user_login.action" method="post">
 
  <table cellpadding="10"  style="border: thick;" >
  <th colspan="2"><h3>登 录</h3></th>
   <tr><th>账号：</th><th><input type="text" name="user.account" id="account"> </th></tr>
   <tr><th>密码：</th><th><input type="password" name="user.password" id="password"></th></tr>
   <tr><th colspan="2"><input type="submit"  value="登 录">&nbsp;&nbsp;&nbsp;<input type="reset" value="重 置"></th></tr>
   </table>
   <s:fielderror cssStyle="color:red;list-style:none" />
  </form>
  </div>
  </body>
  <s:debug></s:debug>
</html>
