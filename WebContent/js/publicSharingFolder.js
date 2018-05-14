/**
 * 公用共享文件夹
 */
var msg = document.getElementById('msg').value;
		var file = window.parent.document.getElementById('file').value;
		if (file.length > 0) {
			if (msg == "") {//初始页面，清除提醒
				window.parent.document.getElementById('msg').innerHTML = "";
			}else if(msg!="上传成功"){//只进一次这个部分的代码
				
				//alert(window.parent.document.getElementById('bottomText').innerHTML);
				var fileNum = window.parent.document.getElementById('bottomText').innerHTML;
				//alert(msg);
				if(msg!="0"){
					if(msg=="1")
						window.parent.document.getElementById('bottomText').innerHTML = new Number(fileNum) + 1;
					window.parent.document.getElementById('msg').innerHTML = "上传成功";
				}else{
					window.parent.document.getElementById('msg').innerHTML = "上传失败";
				}
				document.getElementById('msg').value = "上传成功";//只进一次这个部分的代码
			} 
		}
		function download(path, name) {
			// /LAN_file_sharing_and_retrieval/download.do?path=${f.getAbsolutePath().replaceAll('\\\\','/')}&name=${f.getName()}
			//1.提示下载中
			//document.getElementById(name).innerHTML = "下载中";
			//2.下载成功
			path = encodeURI(path);
			//alert("path0:"+path);
			path = encodeURI(path);
			//alert("path1:"+path);
			//name = encodeURI(name);
			//alert("name0:"+name);
			//name = encodeURI(name);
			//alert("name1:"+name);
			window.location.href = "/LAN_file_sharing_and_retrieval/download.do?path="
					+ path + "&name=" + name;
		}

		function AOpen(openPath) {
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