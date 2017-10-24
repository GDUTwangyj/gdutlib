<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css"> 
    .nobr  br{display:none}   
</style>
	<script type="text/javascript"  src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function(){
			if($("#message ul span").html().length>0){
			alert($("#message ul span").html());
			}
			
			$("#account").blur(function(){
				$.ajax({
					
					url:"user_checkName.action",
					data:"name="+$(this).val(),
					dataType:"json",
					type:"post",
					success:function(data){
						$("#lname").text(data.msg).css("color","red").attr("name","account").addClass("account");
					},
					error:function(){
						alert("系统繁忙，稍后重试！");
					}
				});
			});
		});
	</script>
  </head>
  
  <body style="background-color:BBCCDD">
  <div style="height: 50px;">
  </div>
    <div align="center" >
  <s:form action="user_register.action" method="post"  name="form1" >
    <table cellpadding="10"   >
    <th colspan="2"><h3>注 册</h3></th>
    <tr><th>账号：</th><th><s:textfield id="account" name="user.account"></s:textfield><label id="lname"> </label><s:fielderror cssStyle="color:red;list-style:none"  fieldName="error"  /></th></tr>
    <tr><th>姓名：</th><th><s:textfield id="uname" name="user.uname"></s:textfield></th></tr>
    <tr><th>密码：</th><th><s:password id="password" name="user.password" /></th></tr>
   	 <tr><th>省市：</th><th ><div class="nobr"><s:doubleselect 
   			formName="form1"
	   		doubleList="map[top.id]" 
	   		 doubleListKey="cname" 
	   		 doubleListValue="cname" 
	   		  doubleName="city" 
	   			doubleHeaderValue="--请选择--"
	   			doubleHeaderKey=""
	   		list="prolist" 
	   		 listKey="name"  
	   		  listValue="name" 
	   		   name="province"
	   			headerKey="" 
	   			 headerValue="--请选择--"  
	   			 /></div>
   		</th></tr>
   		<tr><th>街道：</th><th><s:textarea rows="3" name="street"></s:textarea></th></tr>
   		<tr><th colspan="2"><input type="checkbox" value="1"  name="check">阅读并同意本公司协议</th></tr>
   		<tr><th colspan="2"><s:submit value="提 交"></s:submit> <s:reset value="重 置"></s:reset></th></tr>
   		</table>
			<div id="message" style="display:none"><s:fielderror /></div>
		<div></div>
    </s:form>
   		
</div>
  </body>
   		
  <s:debug></s:debug>
</html>
