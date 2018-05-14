<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
</head>
<body>

	<table class="all">
		<tbody>
			<tr>
				<td class="top">
					<table class="top">
						<tbody>
							<tr>
								<td class="logo"></td>
								<td class="logo-title">局域网文件共享及检索系统<br>LAN file
									sharing and retrieval
								</td>
								<td class="logo-title" align="right">${visitorIp}<input
									id="visitorIp" type="hidden" value="${visitorIp}" /></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td class="center">
					<table class="center">
						<tbody>
							<tr>
								<td class="menu">
									<table class="menu">
										<tbody>
											<tr class="menuTitle" id="publicGXFolder"
												style="color: #169FE6; background: #E3E8EC;"
												onmousemove="setBKColor(this)"
												onclick="setBKColorOnClick(this)">
												<td>公用共享文件夹</td>
											</tr>
											<tr class="menuTitle" id="meSharingFolder"
												onmousemove="setBKColor(this)"
												onclick="setBKColorOnClick(this)">
												<td>我的共享文件夹</td>
											</tr>
											<tr class="menuTitle" id="fileTransfer"
												onmousemove="setBKColor(this)"
												onclick="setBKColorOnClick(this)">
												<td>文件传输</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td class="content">
									<table class="content">
										<thead id="seTThead">
											<tr>
												<td><input id="lastButton" class="input-search"
													style="width: 32px" type="button" onclick="last()"
													value="&lt;" disabled="disabled" /><br></td>
												<th><input class="input-search" type="text"
													id="currentPath" value="${currentPath}" readonly="readonly" />
												</th>
												<th><input align="right" class="input-search"
													type="text" placeholder="检索当前目录"
													oninput="javascript:search(this.value)" /></th>
											</tr>
											<tr>
												<th width="36px"></th>
												<th align="left">名称</th>
												<th align="left" width="150px">操作</th>
											</tr>
										</thead>

										<tbody>
											<tr>
												<td colspan="3">
													 
													<iframe id="page" frameborder="0" height="100%"
														width="100%"
														src="/LAN_file_sharing_and_retrieval/toPublicSharingFile.do"></iframe>
												</td>

											</tr>
										</tbody>
										<tfoot id="seTTfoot">
											<tr>
												<th align="left" colspan="3">共<b id="bottomText">${size}</b>个文件/目录
													<img title="新建目录" alt="新建目录"
													src="/LAN_file_sharing_and_retrieval/image/addFolder.png"
													onclick="addFolder()" /> <!--   <input type="button" onclick="addFolder()" value="新建目录" /> 
												--> <input type="file" id="file" /> <img id="upLoadButton"
													title="上传" alt="上传"
													src="/LAN_file_sharing_and_retrieval/image/upload.png"
													onclick="javascript:upLoad()" /> <!-- <input id="upLoadButton" type="button" onclick="javascript:upLoad()" value="上传" />
												 --> <b id="msg"></b>
												</th>

											</tr>
										</tfoot>

									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="js/home.js" type="text/javascript"></script>
</body>
</html>