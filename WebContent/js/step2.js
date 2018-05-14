/**
 * step2
 */
function AOpen(openPath) {

			if(window.parent.parent.document.getElementById('currentPath').value.indexOf("smb")!=-1){
				window.parent.parent.document.getElementById('currentPath').value = openPath;
			}else{
				window.parent.parent.document.getElementById('currentPath').value = openPath + "/";
			}
			//alert("Aopen");
			//alert(openPath);
			openPath = encodeURI(openPath);
			openPath = encodeURI(openPath);
			//alert(openPath);
			window.location.href = "/LAN_file_sharing_and_retrieval/toOpenStep2.do?openPath="
					+ openPath;
						
		}
		
		function transfer(path) {
			//alert("transfer()");
			//alert(document.getElementById('file').value);			
			//var path = document.getElementById('file').value;
			//alert(path);
			//path = path.replace(/\\/g, "/");
			
			var fileName = path.substring(path.lastIndexOf('/') + 1);
				path = encodeURI(path);// 1.编码
				//path = encodeURI(path);// 2.编码
				fileName = encodeURI(fileName);
				//fileName = encodeURI(fileName);
				//alert(path);
				//alert(fileName);	
			var receiveIP = window.parent.document.getElementById('receiveIP').value;
			$.ajax({
				url : "/LAN_file_sharing_and_retrieval/transfer.do", // 要提交的URL路径
				type : "post", // 发送请求的方式
				data : {
					"receiveIP" : receiveIP,
					"path" : path,
					"fileName" : fileName
				}, // 要发送到服务器的数据
				dataType : "json", // 指定传输的数据格式
				success : function(result) {// 请求成功后要执行的代码
					//alert("just ok"); 
					alert(result.msg);
					if (result.msg == "transfer success") {
						window.parent.next();
					}
				}
			});
		}