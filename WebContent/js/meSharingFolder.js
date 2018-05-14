/**
 * 我的共享文件夹
 */
function AOpen(openPath) {

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