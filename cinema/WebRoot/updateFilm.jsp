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
		$(function(){
			$.ajax({
				url:"film_loadtype.action",
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.ftlist!=null&&data.ftlist.length>0){
							$("#type").html("");
							html="";
							$.each(data.ftlist,function(i,ftype){
								html=html+'<option value="'+ftype.id+'">'+ftype.name+'</option>';
							});
					}else{
					}	
					$("#type").html(html);
				},
				error:function(){
						alert("系统出错！");
				}
			});
		});
	</script>
  </head>
  
  <body>
  <form action="film_update.action" method="post" enctype="multipart/form-data">
  <input type="hidden" name="film.id" value="${film.id }"/>
  电影名称：<input type="text" name="film.name" value=${film.name }><br>
  出演明星：<input type="text" name="film.star" value=${film.star }><br>
  电影类型：<select id="type" name="type">
  </select><br>
  电影详情：<textarea rows="5" cols="" name="film.content">${film.content }</textarea><br>
 电影 海报：<input type="file" name="upload">
  <br>
<input type="submit" value="提 交">&nbsp;&nbsp;<input type="reset" value="重 置" ><br>
  
  </form>
  </body>
</html>
