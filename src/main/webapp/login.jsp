<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="js/demo.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		

		
		<style type="text/css">
			.fieldlabel {
				display:block;
				margin-top:20px;
				font-size:13px;
				font-family:微软雅黑;
			}
			.fieldinput {
				width:200px;
				font-family:微软雅黑;
				font-size:13px;
			}
			.checknuminput {
				width:100px;
			}
			.checkNumStyle {
				vertical-align:middle;
			}
		</style>
		
		
		<title>EPM数据管理系统V1.0</title>
	</head>
	<body>
		<div id='loginWin'>
		
			<div style="float:left">
				<img alt="login picture" src="<%=ctx %>/images/loginleft.jpg">
			</div>
			  
			<div class='right' style="padding:30px">
			
				<form id='loginForm' action="login" method="post">
				
					<div>			
						<label for="username" class='fieldlabel'>请输入登录用户名:</label>
						<input id="username" name="username" class="easyui-textbox" type="text" style="width:200px;height:20px">
					</div>
					
					<div>
						<label for="userpass" class="fieldlabel">请输入登录密码:</label>
						<input id="userpass" name="password" class="easyui-textbox" type="password" style="width:200px;height:20px" />
					</div>					
		
                     <div>
                     &nbsp;
                     </div>
                     <div>
                     &nbsp;
                     &nbsp;
                     &nbsp;
			         <input id="rememberMe" name="rememberMe" type="hidden"/>
                     </div>
					<div style="text-align:center">
						<!--  
						<a href="#" id="registBtn" class="easyui-linkbutton" iconCls="icon-man" style="margin-top:30px;width:80px;height:30px">注册</a>
						-->
						<a href="javascript:void(0)" id="registBtn" class="easyui-linkbutton" data-options="iconCls:'icon-man'" onclick="regist()">用户</a>
						&nbsp;
						<!--  
						<a href="#" id="loginBtn" class="easyui-linkbutton" iconCls="icon-ok" style="margin-top:30px;width:80px;height:30px">登录</a>
						-->
						<a href="javascript:void(0)" id="loginBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="loginForm()">登录</a>
				    </div>
			    </form>
		    </div>
		    

	</div>
	
		</div>
		
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		
		<!--  
		<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="../js/jquery.form.js?jsverson=1"></script>
		<script type="text/javascript" src="../js/login/login.js?jsverson=1"></script>
		-->
		
		
		<script type="text/javascript">
		var index = 0;
		
			if (top.location != self.location){ 
				top.location.href="/login.jsp"; 
			}
			$(function(){
				$('#loginWin').window({
					title:'EPM数据管理系统V1.0',
				    width:650,
				    height:324,
				    modal:true,
				    collapsible:false,
				    minimizable:false,
				    maximizable:false,
				    closable:false,
				    resizable:false,
				    draggable : false
				});
				
				document.onkeydown=function(event){
					e = event ? event :(window.event ? window.event : null); 
					if(e.keyCode==13){
						loginForm();
					}
				}
			});
			
			function regist(){
				$.messager.alert("系统登录","用户注册功能暂不提供","info");
			}
			
			function loginForm() {
				index = 1;
				// 点击登录系统按钮,进行Ajax数据请求验证
				// 1.当前Form输入信息校验
				var userNameVar = $("input[name='j_username']").val();
				if (userNameVar == null || userNameVar == '') {
					$.messager.alert("系统登录", "登录用户名不能为空", "warning");
				} else {
					$("input[name='j_password']").blur();
					var userPassVar = $("input[name='j_password']").val();
					if (userPassVar == null || userPassVar == '') {
						$.messager.alert("系统登录", "登录密码不能为空", "warning");
					} else {
						$("#loginForm").form('submit');
					}
				}
			}

		</script>
	</body>
</html>
