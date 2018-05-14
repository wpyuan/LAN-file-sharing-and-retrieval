<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/publicSharingFolder.css" />
</head>
<body>
	<input type="hidden" id="msg" value="${msg}" />
	<table class="content">
		<tbody>
			<c:forEach items="${fileArray}" var="f">
				<tr>
					<c:if test="${f.isDirectory()}">
						<td width="32px"><img alt="文件夹"
							src="/LAN_file_sharing_and_retrieval/image/folder.png"></td>
					</c:if>
					<c:if test="${!f.isDirectory()}">
						<td class="fileType">${f.getName().substring( f.getName().lastIndexOf('.') + 1 )}</td>
					</c:if>
					<td>${f.getName()}</td>

					<td width="150px" id="${f.getName()}"><c:if
							test="${!f.isDirectory()}">
							<a
								href="javascript:download('${f.getAbsolutePath().replaceAll('\\\\','/')}','${f.getName()}')">
								下载</a>
						</c:if> <c:choose>
							<c:when test="${f.isDirectory()}">
								<a
									href="javascript:AOpen('${f.getAbsolutePath().replaceAll('\\\\','/')}')">
									打开</a>
							</c:when>

						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="js/publicSharingFolder.js" type="text/javascript"></script>
</body>
</html>