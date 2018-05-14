<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/fileTransfer.css" />
</head>
<body>
	<div class="content">
		<table>
			<thead>
				<tr>
					<td id="step1Circle" class="currentCircle">1</td>
					<th><div id="proRectTwo" class="rect"></div>
					<th>
					<td id="proCirTwo" class="circle">2</td>
					<th><div id="proRectTre" class="rect"></div>
					<th>
					<td id="proCirTre" class="circle">3</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="currentProcess" colspan="3">连接指定IP</td>
					<td id="proTxtTwo" colspan="3">选择传输文件</td>
					<td id="proTxtTre" colspan="2">完成</td>
				</tr>
			</tbody>
		</table>

		<div id="stepOne">
		<hr /><br>
			对方IP: <input type="text" id="receiveIP" value="" /> <input
				type="button" value="测试连接" onclick="testLink()" /> <br>
		</div>

		<div id="stepTwo" style="display: none;height: 88%">
			<!-- <input id="file" type='file' /> 
			文件路径：<input type="text" id="file" /> <input type="button" value="传送"
				onclick="transfer()" />
			-->
			<hr />
			<iframe id="page" frameborder="0" height="100%" width="100%"
				src="/LAN_file_sharing_and_retrieval/showShareFolder.do"></iframe>
				 
		</div>
	</div>
	<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="js/fileTransfer.js" type="text/javascript"></script>
</body>
</html>