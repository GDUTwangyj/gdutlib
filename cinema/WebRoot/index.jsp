<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"  src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function(){
			/* $.ajax({
				url:"film_loadData.action",
				dataType:"json",
				type:"post",
				success:function(data){
					$("#tbody").html("");
					var html="";
					if(data.list!=null&&data.list.length>0){
						$.each(data.list,function(i,film){
							html=html+'<tr><td>'+film.id+'</td>';
							html=html+'<td><a href="film_content.action?fid='+film.id+'">'+film.name+'</a></td>';
							html=html+'<td>'+film.filmType.name+'</td>';
							html=html+'<td>'+film.star+'</td>';
							html=html+'<td><a href="film_toUpdateFilm.action?fid='+film.id+'">修改</a>&nbsp;&nbsp;&nbsp;<a href="film_delete.action?fid='+film.id+'">删除</a></td></tr>';
						});
					}else{
						html.push('<tr><th colspan="7" align="center"><font color="red">没有找到数据</font></th></tr>');
					}		
					$("#tbody").html(html);
					$("#tbody tr:odd").css("background-color","B0C4DE");
				},
				error:function(){
						alert("系统出错！");
				}
			}); */
			$.ajax({
				url:"film_loadtype.action",
				dataType:"json",
				type:"post",
				success:function(data){
					$("#type").html("");
					if(data.ftlist!=null&&data.ftlist.length>0){
							html="<option value=0>--请选择--</option>";
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
		function addFilm() {
			location.href="addFilm.jsp";
		}
		
		function jPage() {
			var jp = document.getElementById("jumpPage").value;
			var pageCount = document.getElementById("pageCount").value;
			if(isNaN(jp)){
			alert("请输入数字！！");
			}
			if(jp>pageCount){
			alert("总共有"+pageCount+"页，请输入小于或等于"+pageCount+"的值");
			}else if(jp<1){
			alert("请输入大于0的数字");
			}else{
				pageNows(jp);
			}
		}
		function pageNows(page){
			$.ajax({
				type:"post",
				url:"film_query.action?pb.pageNow="+page,
				data:$("#form").serialize(),
				dataType:"json",
				success:function(data){
					var pb = data.pb;
					var film = data.pb.list;					
					var tr = "";
					var info = "";
					if(film!=null&&film.length>0){
						for(var i=0;i<film.length;i++){
							tr=tr+"<tr><td>"+film[i].id+"</td><td><a href=film_content.action?fid="+film[i].id+">"
							+film[i].name+"</a></td><td>"+film[i].filmType.name+"</td><td>"
							+film[i].star+"</td><th><a href=film_toUpdateFilm.action?fid="+film[i].id+">修改</a></th></tr>";
						}
					}else{
						tr="<tr><th colspan=5 align=center><font color=red>没有找到数据</font></th></tr>";
					}					
					$("#tb").html(tr);	
					$("#tb tr:odd").css("background-color","B0C1D2");
					info = info+"<tr><th colspan=5 >当前"+pb.pageNow+"页/共"+pb.pageCount+"页，每页显示"+pb.pageSize+"条记录，共"+pb.rowCount+"条记录";
					if(pb.pageNow>1){
						info = info+"<a href=javascript:pageNows(1)>首页</a>&nbsp;&nbsp;"
								+"<a href=javascript:pageNows("+(pb.pageNow-1)+") >上一页</a>&nbsp;&nbsp;";
					}else{
						info = info+"首页&nbsp;&nbsp;上一页&nbsp;&nbsp;";
					}
					if(pb.pageNow<pb.pageCount){
						info = info+"<a href=javascript:pageNows("+(pb.pageNow+1)+")>下一页</a>&nbsp;&nbsp;"
								+"<a href=javascript:pageNows("+pb.pageCount+")>末页</a>&nbsp;&nbsp;";
					}else{
						info=info+"下一页&nbsp;&nbsp;末页&nbsp;&nbsp;";
					}
					info = info +",<input type=text width=2 id=jumpPage value="+pb.pageNow+" size=2 />页<input type=button value=跳转  onclick=jPage() />"
	  						+"</th></tr>";
					$("#tf").html(info);
					$("#tf tr:odd").css("background-color","B0C4DE");
				},
				error:function(){
					alert("系統繁忙，請稍後再試！");
				}
			});
		}
	</script>
  </head>
  
  <body>
  <div style="height: 80px;"><center><h1>豆 瓣 电 影</h1></center></div>
  <form action="" method="post" id="form"><center>
  电影名称：<input type="text" name="movieName">&nbsp;&nbsp;明星：<input type="text" name="stars"> &nbsp;&nbsp;电影类型：<select id="type" name="type">
  </select>
  &nbsp;&nbsp;<input type="button" value="查询" onclick="pageNows(1)">&nbsp;&nbsp;<input type="button" value="添加" onclick="addFilm()"><br>
  <div style="height: 50px;"></div>
  <table cellpadding="10" cellspacing="1" width="100%">
  <thead>
  <tr style="background-color:B0C1D2;"><td>编号</td><td>名称</td><td>类型</td><td>明星</td><td>操作</td></tr></thead>
<tbody id="tb">
		<c:forEach var="films" items="${pb.list }" varStatus="s">
	      <tr <c:if test="${s.count%2==0 }">style="background-color:B0C1D2;"</c:if>>
	      	<td>${films.id }</td>
	      	<td><a href="film_content.action?fid=${films.id }">${films.name }</a></td>
	      	<td>${films.filmType.name }</td>
	      	<td>${films.star }</td>
	      	<th><a href="film_toUpdateFilm.action?fid=${films.id }">修改</a></th>
	      </tr>
	  	</c:forEach>
	  </tbody>
	  <tfoot id="tf">
	  	<tr>
	  		<th colspan="5">	  	
		      	当前${pb.getPageNow() }页/共${pb.getPageCount() }页，每页显示
				${pb.getPageSize() }条记录，共${pb.getRowCount() }条记录
		      	<c:choose>
					<c:when test="${pb.getPageNow()>1 }">
						<a href="javascript:pageNows(1)">首页</a>&nbsp;&nbsp;
						<a href="javascript:pageNows(${pb.getPageNow()-1 })">上一页</a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						首页&nbsp;&nbsp;上一页&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pb.getPageNow()<pb.getPageCount() }">
						<a href="javascript:pageNows(${pb.getPageNow()+1 })">下一页</a>&nbsp;&nbsp;
					 	<a href="javascript:pageNows(${pb.getPageCount() })">末页</a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						下一页&nbsp;&nbsp;末页&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
					 ,<input type="text" width="2" id="jumpPage" value="${pb.getPageNow() }" size=2>页<input type="button" value="跳转" onclick="jPage()"/>
	  		</th>
	  	</tr>	  
	  </tfoot>
  </table>
  <input type="hidden" id="pageCount" value="${pb.getPageCount() }">   
  </center>
  </form>
  </body>
</html>
