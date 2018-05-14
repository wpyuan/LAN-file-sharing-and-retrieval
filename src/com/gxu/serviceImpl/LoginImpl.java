package com.gxu.serviceImpl;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxu.service.LoginService;
import com.gxu.utils.ShareFolder;

import jcifs.smb.SmbFile;

@Service
@Transactional
public class LoginImpl implements LoginService {

	private ShareFolder shareFolder;
	
	@Override
	public File[] getFileArray(String path, HttpServletRequest request) throws Exception {

		File file = new File(path);
		return file.listFiles();
	}

	@Override
	public String getVisitorIp(HttpServletRequest request) throws Exception {
		System.out.println("---LoginImpl.getVisitorIp()---");

		String visitorIp = null;
		if (request.getHeader("x-forwarded-for") == null) {
		
			visitorIp = request.getRemoteAddr();
			System.out.println("visitorIp0="+visitorIp);
		} else {
			
			visitorIp = request.getHeader("x-forwarded-for");
			System.out.println("visitorIp1="+visitorIp);
		}
		
		if (visitorIp.equals("0:0:0:0:0:0:0:1")) {
			
			Enumeration<NetworkInterface> nifs = null;
			try {
				nifs = NetworkInterface.getNetworkInterfaces();
				while (nifs.hasMoreElements()) {
					NetworkInterface nif = nifs.nextElement();


					Enumeration<InetAddress> addresses = nif.getInetAddresses();
					while (addresses.hasMoreElements()) {
						InetAddress addr = addresses.nextElement();
						
						if (addr instanceof Inet4Address && !nif.getName().equals("lo")
								&& !addr.getHostAddress().equals("127.0.0.1")
								&& !nif.getDisplayName().contains("VMware Virtual Ethernet Adapter for")) { 
																											
							visitorIp = addr.getHostAddress();
							break;
						}
					}
				}
			} catch (SocketException e) {
				System.out.println("LoginController.GetIp SocketException:" + e.getMessage());
				e.printStackTrace();
			}
		}

		return visitorIp;
	}

	@Override
	public String createShareFolder(HttpServletRequest request) throws Exception {
		
		String visitorIp = (String) request.getSession().getAttribute("visitorIp");
		if (!visitorIp.equals("192.168.1.17")) {//客户端
			System.out.println("服务器");

			try {
				String url = "smb://" + visitorIp + "/Users/fileGX";
				SmbFile file = new SmbFile(url);

				if (!file.exists()) {
					System.out.println("not exist");
					file.mkdirs();
				} else {
					System.out.println("exist");
				}
				file = new SmbFile(url);
				if (file.exists() && file.isDirectory()) {
					System.out.println("create success");
					return "[C:/Users/fileGX] init success!";
				} else {
					System.out.println("create faild");
					return "error";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else {
			System.out.println("服务器");
			String path = "C:/Users/fileGX" ;			
			if (!shareFolder.isExist(path)) {
				shareFolder.createFolder(path);
				if (shareFolder.isExist(path)) {
					System.out.println("create success");
					return "[C:/Users/fileGX] init success!";
				}else { 
					System.out.println("create faild");
					return "error"; 
				}
			}
		}
		return "[C:/Users/fileGX] init success!";
	}

}
