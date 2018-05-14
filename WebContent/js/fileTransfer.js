/**
 * 文件传输
 */
var pro = 1; //当前进度
		var tmd = 1;//透明读  0.0（完全透明）与 1.0（完全不透明)
		var R = 50, G = 155, B = 50;
		var flag = 0;
		setInterval(changeCirBKColor, 9);
		function changeCirBKColor() {

			/*if (flag == 0) { //++
				if (R < 255) {
					R += 5;
					G -= 5;
					B += 5;
				} else flag = 1;
			} else {
				if (R > 0) {
					R -= 5;
					G += 5;
					B -= 5;
				}else flag = 0;
				
			}*/
			if (flag == 0) { //++
				if (tmd > 0) {
					tmd -= 0.01;
				} else
					flag = 1;
			} else {
				if (tmd < 1) {
					tmd += 0.01;
				} else
					flag = 0;

			}
			if (pro == 1) {
				//document.getElementById('step1Circle').style.background = "RGB("
				//		+ R + "," + G + "," + B + ")";
				document.getElementById('step1Circle').style.background = "RGBA(0,255,0,"
						+ tmd + ")";
			} else if (pro == 2) {
				document.getElementById('step1Circle').style.background = "RGBA(0,255,0,1)";
				document.getElementById('proCirTwo').style.background = "RGBA(0,255,0,"
						+ tmd + ")";
			} else if (pro == 3) {
				document.getElementById('proCirTwo').style.background = "RGBA(0,255,0,1)";
				document.getElementById('proCirTre').style.background = "RGBA(0,255,0,1)";
			}
		}

		function next() {
			//alert(pro);
			if (pro < 3) {
				if (pro == 1) {
					document.getElementById('stepOne').style.display = "none";
					document.getElementById('stepTwo').style.display = "";
					document.getElementById('proCirTwo').className = "currentCircle";//圆形
					document.getElementById('proRectTwo').className = "currentRect";//矩形
					document.getElementById('proTxtTwo').className = "currentProcess";//文字
				} else if (pro == 2) {

					document.getElementById('proCirTre').className = "currentCircle";//圆形
					document.getElementById('proRectTre').className = "currentRect";//矩形
					document.getElementById('proTxtTre').className = "currentProcess";//文字
				}
				pro++;
			}
		}

		function testLink() {
			var receiveIP = document.getElementById('receiveIP').value;
			//alert("testLink() go on");
			$.ajax({
				url : "/LAN_file_sharing_and_retrieval/testLink.do", // 要提交的URL路径
				type : "post", // 发送请求的方式
				data : "receiveIP=" + receiveIP, // 要发送到服务器的数据
				//dataType : "json", // 指定传输的数据格式
				success : function(result) {// 请求成功后要执行的代码
					alert("link " + result);
					if (result == "success") {
						//alert("just ok");
						next();
					}

				}
			});

		}