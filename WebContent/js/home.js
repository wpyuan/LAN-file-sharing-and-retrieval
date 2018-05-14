/**
 * 2018.0306.15点48分
 */
var pre;// 原来的节点，假设原来的背景颜色为#EFF4F8，悬停变为#E3E8EC
var click = document.getElementById('publicGXFolder');
function setBKColor(sObject) {
	if (sObject != click) {// 鼠标悬停不在鼠标选中位置
		$(sObject).attr("style", "BACKGROUND-COLOR: #E3E8EC");
	}
	if (pre != null && pre != sObject && pre != click) {// 上一次鼠标悬停位置除了（当前悬停位置和点击位置）的其他位置
		$(pre).attr("style", "BACKGROUND-COLOR: #EFF4F8;COLOR: #424E67");
	}
	if (pre == click) {// 如果上一次悬停位置与点击位置一样
		$(pre).attr("style", "COLOR: #169FE6;BACKGROUND-COLOR: #E3E8EC");
	}
	pre = sObject;
}
function showJSP(sObject){
	if (sObject.id == "publicGXFolder") {
		// seTThead、seTTfoot 表头、表底 都显示
		document.getElementById("seTThead").style.display = "";
		document.getElementById("seTTfoot").style.display = "";

		document.getElementById("currentPath").value = "C:/Users/fileGX/";
		document.getElementById("upLoadButton").disabled = "";// 有效
		document.getElementById("file").disabled = "";// 有效
		document.getElementById("lastButton").disabled = "disabled";// 上一步按钮失效
		$
				.ajax({
					url : "/LAN_file_sharing_and_retrieval/fileOrFolderNumber.do", // 要提交的URL路径
					type : "post", // 发送请求的方式
					data : "type=" + "1", // 要发送到服务器的数据
					dataType : "json", // 指定传输的数据格式
					success : function(result) {// 请求成功后要执行的代码

						document.getElementById('bottomText').innerHTML = result.size;
						document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toPublicSharingFile.do";
					}
				});
		// document.getElementById('page').src =
		// "/LAN_file_sharing_and_retrieval/toPublicSharingFile.do";
	} else if (sObject.id == "meSharingFolder") {
		// seTThead、seTTfoot 表头、表底 都显示
		document.getElementById("seTThead").style.display = "";
		document.getElementById("seTTfoot").style.display = "";

		if (document.getElementById("visitorIp").value == "192.168.1.17") {

			document.getElementById("currentPath").value = "C:/Users/fileGX/";

		} else {
			document.getElementById("currentPath").value = "smb://"
					+ document.getElementById("visitorIp").value + "/";
		}

		document.getElementById("lastButton").disabled = "disabled";// 上一步按钮失效
		document.getElementById("upLoadButton").disabled = "disabled";// 失效
		document.getElementById("file").disabled = "disabled";// 失效
		// document.getElementById("file").disabled="";//有效

		$
				.ajax({
					url : "/LAN_file_sharing_and_retrieval/fileOrFolderNumber.do", // 要提交的URL路径
					type : "post", // 发送请求的方式
					data : "type=" + "2", // 要发送到服务器的数据
					dataType : "json", // 指定传输的数据格式
					success : function(result) {// 请求成功后要执行的代码

						document.getElementById('bottomText').innerHTML = result.size;
						document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toMeSharingFolder.do";
					}
				});

		// document.getElementById('page').src =
		// "/LAN_file_sharing_and_retrieval/toMeSharingFolder.do";
	} else if (sObject.id == "fileTransfer") {
		// seTThead、seTTfoot 表头、表底 都隐藏
		document.getElementById("seTThead").style.display = "none";
		document.getElementById("seTTfoot").style.display = "none";

		//document.getElementById("lastButton").disabled = "disabled";// 上一步按钮失效
		$
				.ajax({
					url : "/LAN_file_sharing_and_retrieval/fileOrFolderNumber.do", // 要提交的URL路径
					type : "post", // 发送请求的方式
					data : "type=" + "3", // 要发送到服务器的数据
					dataType : "json", // 指定传输的数据格式
					success : function(result) {// 请求成功后要执行的代码

						document.getElementById('bottomText').innerHTML = result.size;
						document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toFileTransfer.do";
					}
				});
		// document.getElementById('page').src =
		// "/LAN_file_sharing_and_retrieval/toFileTransfer.do";
	} else {
		return;
	}
}
function setBKColorOnClick(sObject) {

	if (sObject != click) {
		$(sObject).attr("style", "BACKGROUND-COLOR: #E3E8EC;COLOR: #169FE6");
		if (click != null && click != sObject) {
			$(click).attr("style", "BACKGROUND-COLOR: #EFF4F8;COLOR: #424E67");
		}
		click = sObject;
		pre = click;
		showJSP(sObject)
	}
}
// 上传
function upLoad() {
	// alert(click.id);
	if (click.id == "publicGXFolder") {
		var srcPath = document.getElementById('file').value;
		// alert(srcPath);
		if (srcPath.length > 0) {
			// alert( srcPath.substring(srcPath.lastIndexOf('\\')+1) );

			var fileName = srcPath.substring(srcPath.lastIndexOf('\\') + 1);
			var destPath = document.getElementById('currentPath').value;
			// alert(srcPath);
			srcPath = srcPath.replace(/\\/g, "/");
			// alert(srcPath);
			// alert(fileName);
			document.getElementById('msg').innerHTML = "正在上传...";

			srcPath = encodeURI(srcPath);// 1.编码
			srcPath = encodeURI(srcPath);// 2.编码

			fileName = encodeURI(fileName);// 1.编码
			fileName = encodeURI(fileName);// 2.编码

			document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/upLoad.do?srcPath="
					+ srcPath
					+ "&fileName="
					+ fileName
					+ "&destPath="
					+ destPath;

			/*
			 * window.location.href =
			 * "/LAN_file_sharing_and_retrieval/upLoad.do?srcPath=" + srcPath +
			 * "&fileName=" + fileName + "&destPath=" + destPath;
			 */
		} else {
			alert("error:未选择文件！");
		}
	} else if (click.id == "meSharingFolder") {

	}
}
function addFolder() {

	var fileName = prompt("新建文件夹", "输入文件名");
	if (fileName.length > 0) {
		if (fileName == "输入文件名" || fileName.trim() == "") {
			alert('error:未正确命名！输入一个新的文件名');
			return;
		}
		// alert(fileName);
		var currentPath = document.getElementById('currentPath').value;
		if (click.id == "publicGXFolder") {
			$
					.ajax({
						url : "/LAN_file_sharing_and_retrieval/createFolder.do",// 要提交的URL路径
						type : "post", // 发送请求的方式
						data : {// 要发送到服务器的数据
							"type" : "1",
							"fileName" : fileName,
							"currentPath" : currentPath
						},
						dataType : "json", // 指定传输的数据格
						success : function(result) {// 请求成功后要执行的代码
							alert(result.msg);
							if (result.msg == "在当前路径下创建目录成功！") {
								var fileNum = document
										.getElementById('bottomText').innerHTML;
								document.getElementById('bottomText').innerHTML = new Number(
										fileNum) + 1;
								currentPath = encodeURI(currentPath);
								currentPath = encodeURI(currentPath);
								document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toPublicSharingFile.do?openPath="
										+ currentPath;
							}
						}
					});
		} else if (click.id == "meSharingFolder") {

			$
					.ajax({
						url : "/LAN_file_sharing_and_retrieval/createFolder.do",// 要提交的URL路径
						type : "post", // 发送请求的方式
						data : {// 要发送到服务器的数据
							"type" : "2",
							"fileName" : fileName,
							"currentPath" : currentPath
						},
						dataType : "json", // 指定传输的数据格
						success : function(result) {// 请求成功后要执行的代码
							alert(result.msg);
							if (result.msg == "在当前路径下创建目录成功！") {
								var fileNum = document
										.getElementById('bottomText').innerHTML;
								document.getElementById('bottomText').innerHTML = new Number(
										fileNum) + 1;
								document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toOpenMeSharingFolder.do?openPath="
										+ currentPath;
								// window.location.href =
								// "/LAN_file_sharing_and_retrieval/toHome.do";
							}
						}
					});

		}

	} else {
		alert("error:未正确命名！文件名不为空");
	}

}
// 上一步
function last() {
	// lastButton
	var currentPath = document.getElementById('currentPath').value;
	if (currentPath.indexOf('C:/Users/fileGX/') != -1
			&& currentPath.length == 16) {// 公用共享文件上一步
		document.getElementById("lastButton").disabled = "disabled";// 上一步按钮禁用
		return false;
	}

	// 当前路径
	// alert(currentPath);
	// 上一步路径
	currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));

	if (currentPath.lastIndexOf('/') == 5) {// 我的共享文件夹
		document.getElementById("lastButton").disabled = "disabled";// 上一步按钮禁用
		return false;
	}

	document.getElementById('currentPath').value = currentPath.substring(0,
			currentPath.lastIndexOf("/") + 1);
	if (document.getElementById('currentPath').value.indexOf('smb') == -1) {
		returnOpen(document.getElementById('currentPath').value);
	} else {
		returnSmbOpen(document.getElementById('currentPath').value);
	}

}
function returnOpen(openPath) {
	document.getElementById('currentPath').value = openPath;
	// alert(openPath);
	openPath = encodeURI(openPath);
	openPath = encodeURI(openPath);
	// alert("open");
	// LAN_file_sharing_and_retrieval/toPublicSharingFile.do?openPath=${f.getAbsolutePath().replaceAll('\\\\','/')}
	$
			.ajax({
				url : "/LAN_file_sharing_and_retrieval/fileOrFolderNumber.do?openPath="
						+ openPath, // 要提交的URL路径
				type : "post", // 发送请求的方式
				data : "type=" + "1", // 要发送到服务器的数据
				dataType : "json", // 指定传输的数据格式
				success : function(result) {// 请求成功后要执行的代码
					// alert(result.size);
					document.getElementById('bottomText').innerHTML = result.size;
					document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toPublicSharingFile.do?openPath="
							+ openPath;
				}
			});
}
function returnSmbOpen(openPath) {
	document.getElementById('currentPath').value = openPath;
	// alert(openPath);
	openPath = encodeURI(openPath);
	openPath = encodeURI(openPath);
	// alert("Aopen");
	// LAN_file_sharing_and_retrieval/toPublicSharingFile.do?openPath=${f.getAbsolutePath().replaceAll('\\\\','/')}
	$
			.ajax({
				url : "/LAN_file_sharing_and_retrieval/fileOrFolderNumber.do?openPath="
						+ openPath, // 要提交的URL路径
				type : "post", // 发送请求的方式
				data : "type=" + "2", // 要发送到服务器的数据
				dataType : "json", // 指定传输的数据格式
				success : function(result) {// 请求成功后要执行的代码
					// alert(result.size);
					document.getElementById('bottomText').innerHTML = result.size;
					document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toOpenMeSharingFolder.do?openPath="
							+ openPath;
				}
			});
}

function search(searchInfo) {
	if (searchInfo.length <= 0) {
		showJSP(click);	// 返回当前路径的文件
		return;
	}
	var path = document.getElementById('currentPath').value;
	path = encodeURI(path);
	path = encodeURI(path);
	searchInfo = encodeURI(searchInfo);
	searchInfo = encodeURI(searchInfo);
	// alert(searchInfo);
	var type="";
	if(click.id=="publicGXFolder"){
		type = "1";
	}else if(click.id == "meSharingFolder"){
		type = "2";
		
	}
	document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toQuery.do?searchInfo="
			+ searchInfo + "&path=" + path + "&type=" + type;

}

// 信息检索
function search1(searchInfo) {
	// alert(searchInfo);
	var path = document.getElementById('currentPath').value;
	if (searchInfo.length <= 0) {

		return;
	}

	$
			.ajax({
				url : "/LAN_file_sharing_and_retrieval/getFileOrFolderByInfo.do", // 要提交的URL路径
				type : "post", // 发送请求的方式
				data : {
					"path" : path,
					"searchInfo" : searchInfo,
				// "type=" : "2"
				}, // 要发送到服务器的数据
				dataType : "json", // 指定传输的数据格式
				success : function(result) {// 请求成功后要执行的代码
					// alert(result.fileList.length);
					document.getElementById('bottomText').innerHTML = result.fileList.length;
					/*
					 * $('#page').hide(); $('#queryTable').show();
					 * 
					 * var queryContent = "<table id=\"queryTable\"
					 * class=\"content\"><tbody>"; //var queryContent = "<tbody>";
					 * for(var i=0;i<result.fileList.length;i++){ queryContent += "<tr>";
					 * console.log(result.fileList[i].toString()); if
					 * (result.fileList[i].directory) {//文件夹 queryContent += "<td width=\"32px\"><img
					 * alt=\"文件夹\"
					 * src=\"/LAN_file_sharing_and_retrieval/image/folder.png\"></td>";
					 *  } else {//文件 queryContent += "<td class=\"fileType\">"+result.fileList[i].name.substring(
					 * result.fileList[i].name.lastIndexOf('.') + 1 )+"</td>"; }
					 * queryContent += "<td>"+result.fileList[i].name+"</td>";
					 * queryContent += "<td width=\"150px\">"; if
					 * (result.fileList[i].directory) {//文件夹 queryContent += "<a
					 * href=\"javascript:AOpen('"+result.fileList[i].path+"')\">
					 * 打开</a>";
					 *  } else {//文件 queryContent += "<a
					 * href=\"javascript:delet('"+result.fileList[i].path+"')\">
					 * 删除</a>"; } queryContent += "</tr>"; } queryContent += "</tbody></table><table
					 * height=\"100%\"></table>"; //queryContent += "</tbody>";
					 * $('#queryTable').html(queryContent);
					 */
					/*
					 * <!-- <tbody> <c:forEach items="${fileArray}" var="f">
					 * <tr> <c:choose> <c:when test="${f.isDirectory}">
					 * <td width="32px"><img alt="文件夹"
					 * src="/LAN_file_sharing_and_retrieval/image/folder.png"></td>
					 * </c:when> <c:otherwise> <td class="fileType">${f.name.substring(
					 * f.name.lastIndexOf('.') + 1 )}</td> </c:otherwise>
					 * </c:choose> <td>${f.name}</td> <td width="150px"><c:if
					 * test="${!f.isDirectory}"> <a
					 * href="javascript:delet('${f.path}')"> 删除</a>
					 * 
					 * </c:if> <c:if test="${f.isDirectory}"> <a
					 * href="javascript:AOpen('${f.path}')"> 打开</a> </c:if></td>
					 * </tr> </c:forEach> </tbody> -->
					 */
					for (var i = 0; i < result.fileList.length; i++) {
						console.log(result.fileList[i]);
					}
					document.getElementById('page').src = "/LAN_file_sharing_and_retrieval/toQuery.do";
					/*
					 * document.getElementById('page').src =
					 * "/LAN_file_sharing_and_retrieval/toQuery.do?fileArray=" +
					 * result.fileList;
					 */
					/*
					 * document.getElementById('page').src =
					 * "/LAN_file_sharing_and_retrieval/direct/query.jsp?fileArray=" +
					 * result.fileList;
					 */
				},
				error : function() {
					alert("error");
				}

			});
}