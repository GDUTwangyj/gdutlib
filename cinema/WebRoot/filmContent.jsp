<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改电影信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"  src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function download() {
			location.href="film_down.action?downFileName=${film.path }";
		}
	</script>
  </head>
  
  <body>
  <table border="1" cellpadding="2" width="100%">
  <tr>
  <th rowspan="5"><img alt="海报" src="upload/${film.path }" height="736px"></th>

  	<th colspan="2" height="150px"><h1>${film.name }</h1></th>
  	<tr><th width="100px">主演</th><td>${film.star }</td></tr>
  	<tr><th>类型</th><td>${film.filmType.name }</td></tr>
  	<tr><th>简介</th><td>${film.content }</td></tr>
  	<tr><th colspan="2"><button onclick="download()">下载海报</button><button onclick="javascript:history.back(0)">返回</button></th></tr>

 </tr>
  </table>
  </body>
</html>
