package com.gxu.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxu.service.TransferService;
import com.gxu.utils.CodeChangeUtil;
import com.gxu.utils.ShareFolder;

@Service
@Transactional
public class TransferImpl implements TransferService {

	private CodeChangeUtil codeChangeUtil;
	private ShareFolder shareFolder;

	
	@Override
	public int testlinkByIP(HttpServletRequest request, String receiveIP) throws Exception {
		
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(df.format(day));

		System.out.println(df.format(day).replace(":", "£º"));

		String createPath = "\\\\" + request.getSession().getAttribute("visitorIp") + "\\Users\\fileGX\\"
				+ df.format(day).replace(":", "£º") + ".txt";
		if(!shareFolder.isExist(createPath)) {
			shareFolder.createFile(createPath);
		}
		if (shareFolder.isExist(createPath)) {
			 String srcPath = createPath;
			 String destPath = "\\\\" + receiveIP + "\\Users\\fileGX\\" + df.format(day).replace(":", "£º") + ".txt"; 
			 int flag = shareFolder.copy(srcPath, null, destPath); 
			if (flag!=0) {

				shareFolder.delete(createPath);
				shareFolder.delete(destPath);
				return 1;
			}

			shareFolder.delete(createPath);
			shareFolder.delete(destPath);
		}
		
		return 0;
	}

	@Override
	public boolean transfer(HttpServletRequest request, String receiveIP,String srcPath,String fileName) throws Exception {
		System.out.println("---TransferImpl.transfer()---");
		srcPath = codeChangeUtil.changeUTF8(srcPath);
		fileName = codeChangeUtil.changeUTF8(fileName);
		System.out.println(srcPath);
		String realyPath = srcPath.substring(srcPath.lastIndexOf("/Users")+6); 
		realyPath = realyPath.replace("/", "\\\\");
		System.out.println(realyPath);
		int flag = -1;
		String srcIp = (String) request.getSession().getAttribute("visitorIp");
		
		if (srcIp.equals("192.168.1.17")) {
			
			String destPath = "\\\\"+receiveIP+"\\Users\\fileGX\\"+fileName; 
			flag = shareFolder.copy(srcPath, fileName, destPath);
			if(flag!=0) {
				return true;
			}else return false;
		}
		
		else {
			srcPath = "\\\\" + srcIp + "\\Users" + realyPath;
			String destPath = "\\\\"+receiveIP+"\\Users\\fileGX\\"+fileName; 
			flag = shareFolder.copy(srcPath, fileName, destPath);
			if(flag!=0) {
				return true;
			}else return false;
		}
	}

}
