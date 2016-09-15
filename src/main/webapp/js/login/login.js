/**
 * 登录首页定义的JS操作
 */
var index = 0;

$(function() {
	
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
		if(e.keyCode==13){
			loginForm();
		}
	}

	// 为登录按钮绑定登录操作事件
	$("#loginBtn").bind("click", loginForm);
	
	// 用户注册功能
	$("#registBtn").bind("click",regist);
});

function regist(){
	$.messager.alert("系统登录","用户注册功能暂不提供","info");
}

// 加入触发时间戳
function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	if ((url.indexOf("&") >= 0)) {
		url = url + "×tamp=" + timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}
	return url;
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