/**
 * 登陆界面
 * 1、表单提交 click()
 * 
 */
$('#login-button').click(function (event) {
	
//	var account = document.getElementById('account').value;
//	var password = document.getElementById('password').value;
//	if(account==null||account==''){
//		alert("用户名不为空。");
//		return;
//	}
//	if(password==null||password==''){
//		alert("密码不为空。");\
	
	
	
//		return;
//	}

//	var userIp = document.getElementById('userIp').placeholder;
//	alert(userIp);
	
	//延迟或者繁忙显示
	event.preventDefault();
	$('form').fadeOut(500);
	$('.wrapper').addClass('form-success');
	document.getElementById('msg').innerHTML = '初始化本地共享文件夹fileGX...';
	initShareFolder();
});
function initShareFolder() {
	$.ajax({
		url : "/LAN_file_sharing_and_retrieval/createLocalShareFolder.do", // 要提交的URL路径
		type : "post", // 发送请求的方式
		//data : "type=" + "1", // 要发送到服务器的数据
		//dataType : "json", // 指定传输的数据格式
		success : function(result) {// 请求成功后要执行的代码
			//alert(result);
			document.getElementById('msg').innerHTML = result;
			if(result!="error"){
				window.location.href="/LAN_file_sharing_and_retrieval/toHome.do";
			}else{
				alert("初始化失败，无权限操作。请启用文件共享！");
				window.location.href="/LAN_file_sharing_and_retrieval/toLogin.do";
			}
		}
	});	
}