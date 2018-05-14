<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
 %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href ="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/meSharingFolder.css" />
<title>Insert title here</title>
</head>
<body>
	<table class="content">
		<tbody>
			<c:forEach items="${fileArray}" var="f">
				<tr>
					<c:choose>
						<c:when test="${f.directory}">
							<td width="32px"><img alt="文件夹"
								src="/LAN_file_sharing_and_retrieval/image/folder.png"></td>
						</c:when>
						<c:otherwise>
							<td class="fileType">${f.name.substring( f.name.lastIndexOf('.') + 1 )}</td>
						</c:otherwise>
					</c:choose>
					<td>${f.name}</td>
					<td width="150px"><c:if test="${!f.directory}">
					<a href="javascript:delet('${f.path}')" >
								删除</a>
							 
						</c:if> <c:if test="${f.directory}">
							<a href="javascript:AOpen('${f.path}')" >
								打开</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
	
</body>
</html>