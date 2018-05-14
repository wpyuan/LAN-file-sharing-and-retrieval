/**
 * wpy
 * 2018/4/24
 * query.jsp的js文件	
 */
$(document).ready(function(){
	//alert($('#num').val());
	window.parent.document.getElementById('bottomText').innerHTML = $('#num').val();
});
function AOpen(openPath){
	var type=$('#type').val();
	if (type=="1") {
		AOpen_P(openPath);
	} else if(type=="2"){
		AOpen_M(openPath);
	}
}
function AOpen_P(openPath) {
	window.parent.document.getElementById('currentPath').value = openPath
			+ "/";
	//alert(openPath);
	openPath = encodeURI(openPath);
	openPath = encodeURI(openPath);
	//alert("Aopen");
	//LAN_file_sharing_and_retrieval/toPublicSharingFile.do?openPath=${f.getAbsolutePath().replaceAll('\\\\','/')}
	$
			.ajax({
				url : "/LAN_file_sharing_and_retrieval/fileOrFolderNumber.do?openPath="
						+ openPath, //要提交的URL路径
				type : "post", //发送请求的方式
				data : "type=" + "1", //要发送到服务器的数据
				dataType : "json", //指定传输的数据格式
				success : function(result) {//请求成功后要执行的代码		
					//alert(result.size);
					window.parent.document.getElementById('bottomText').innerHTML = result.size;
					window.parent.document.getElementById("lastButton").disabled = "";//上一步按钮有效
					window.location.href = "/LAN_file_sharing_and_retrieval/toPublicSharingFile.do?openPath="
							+ openPath;
				}
			});
}
function AOpen_M(openPath) {

	if(window.parent.document.getElementById('currentPath').value.indexOf("smb")!=-1){
		window.parent.document.getElementById('currentPath').value = openPath;
	}else{
		window.parent.document.getElementById('currentPath').value = openPath + "/";
	}
	//alert("Aopen");
	openPath = encodeURI(openPath);
	openPath = encodeURI(openPath);
	$.ajax({
				url : "/LAN_file_sharing_and_retrieval/fileOrFolderNumber.do?openPath="
						+ openPath, //要提交的URL路径
				type : "post", //发送请求的方式
				data : "type=" + "2", //要发送到服务器的数据
				dataType : "json", //指定传输的数据格式
				success : function(result) {//请求成功后要执行的代码		
					//alert(result.size);
					window.parent.document.getElementById('bottomText').innerHTML = result.size;
					window.parent.document.getElementById("lastButton").disabled = "";//上一步按钮有效
					window.location.href = "/LAN_file_sharing_and_retrieval/toOpenMeSharingFolder.do?openPath="
							+ openPath;
				}
			});
}
function download(path, name) {
	// /LAN_file_sharing_and_retrieval/download.do?path=${f.getAbsolutePath().replaceAll('\\\\','/')}&name=${f.getName()}
	//1.提示下载中
	//document.getElementById(name).innerHTML = "下载中";
	//2.下载成功
	window.location.href = "/LAN_file_sharing_and_retrieval/download.do?path="
			+ path + "&name=" + name;
}
function delet(path) {
	//alert(path);
	var openPath = "";
	openPath = window.parent.document.getElementById('currentPath').value;
	//alert(openPath);
	$.ajax({
		url : "/LAN_file_sharing_and_retrieval/delet.do", //要提交的URL路径
		type : "post", //发送请求的方式
		data :{//要发送到服务器的数据
			"type" : "2", 
			"openPath" : openPath,
			"filePath" : path
		},
		dataType : "json", //指定传输的数据格式
		success : function(result) {//请求成功后要执行的代码		
			//alert(result.size);
			window.parent.document.getElementById('bottomText').innerHTML = result.size;
			window.parent.document.getElementById("lastButton").disabled = "";//上一步按钮有效
			window.location.href = "/LAN_file_sharing_and_retrieval/toOpenMeSharingFolder.do?openPath="
					+ openPath;
		}
	});
}
