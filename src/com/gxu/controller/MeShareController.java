package com.gxu.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxu.service.HomeService;
import com.gxu.service.MeShareService;
import com.gxu.utils.CodeChangeUtil;
import com.gxu.utils.ShareFolder;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import net.sf.json.JSONObject;

@Controller
public class MeShareController {

	private CodeChangeUtil changeUtil;
	private ShareFolder shareFolder;
	private HomeService homeService;
	private MeShareService meShareService;
	private JSONObject jsonData;
	
	public JSONObject getJsonData() {
		return jsonData;
	}
	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}
	public MeShareService getMeShareService() {
		return meShareService;
	}
	@Resource
	public void setMeShareService(MeShareService meShareService) {
		this.meShareService = meShareService;
	}
	public HomeService getHomeService() {
		return homeService;
	}
	@Resource
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	
	@RequestMapping("/toOpenMeSharingFolder")
	public String toOpenMeSharingFolder(Model model, String openPath, HttpServletRequest request) {

		System.out.println("===MeShareController.toOpenMeSharingFolder()===");
		if (request.getSession().getAttribute("visitorIp").equals("192.168.1.17")) {

			File[] fileArray = null;
			try {
				fileArray = homeService.getFileArray(request, openPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			model.addAttribute("fileArray", fileArray);
			model.addAttribute("size", fileArray.length);
		} 
		else {
			List<SmbFile> files = null;
			try {
				files = meShareService.getClientFileArrayBySmb(openPath);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("fileArray", files);
			model.addAttribute("size", files.size());
		} 
		return "meSharingFolder";
	}
	
	/*
	 * 删除
	 */
	@RequestMapping("/delet")
	@ResponseBody
	public String delet(String openPath,String type,String filePath ,HttpServletRequest request) {
		System.out.println("===MeShareController.delet()===");
		
		/*
		 *1.删除文件
		 */
		//filePath = changeUtil.changeUTF8(filePath);
		if (filePath.indexOf("smb")==-1) {
			shareFolder.delete(filePath);
		}else {
			try {
				shareFolder.smbDelete(filePath);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SmbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 *2. 计算文件数
		 */
		//openPath = changeUtil.changeUTF8(openPath);
		Map map = new HashMap();
		if (type.equals("1")) {
			String path = null;
			if (openPath == null) {
				path = "C:/Users/fileGX/";
			} else {
				path = openPath;
			}
			System.out.println(openPath);
			File file = new File(path);
			File[] fileArray = file.listFiles();
			map.put("size", fileArray.length);

		} else if (type.equals("2")) {

			try {
				if (!request.getSession().getAttribute("visitorIp").equals("192.168.1.17")) { // ���Ƿ���������
					String url = null;
					if (openPath == null) {
						url = "smb://" + request.getSession().getAttribute("visitorIp") + "/";
						List<SmbFile> files = shareFolder.getShareFolder(url);
						map.put("size", files.size());
					} else {
						List<SmbFile> files = meShareService.getClientFileArrayBySmb(openPath);
						map.put("size", files.size());
					}
					
				} else {
					String path = null;
					if (openPath == null) {
						path = "C:/Users/fileGX/";
					} else {
						path = openPath;
					}
					File file = new File(path);
					File[] fileArray = file.listFiles();
					map.put("size", fileArray.length);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {
			map.put("size", 0);
		}

		jsonData = JSONObject.fromObject(map);
		return jsonData.toString();
		
	}
}
