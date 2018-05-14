package com.gxu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxu.entity.FileInfo;
import com.gxu.service.HomeService;
import com.gxu.utils.CodeChangeUtil;
import com.gxu.utils.SearchInfoUtil;
import com.gxu.utils.ShareFolder;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class HomeController {

	private JSONObject jsonData;
	private HomeService homeService;

	public HomeService getHomeService() {
		return homeService;
	}

	@Resource
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	public JSONObject getJsonData() {
		return jsonData;
	}

	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}

	/*
	 * 下载共享文件
	 */
	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response, String path, String name)
			throws UnsupportedEncodingException, IOException {
		System.out.println("===HomeController.download()===");

		try {
			homeService.downLoad(request, response, path, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 跳转 公用共享文件夹
	 */
	@RequestMapping("/toPublicSharingFile")
	public String toPublicSharingFile(HttpServletRequest request, Model model, String openPath) {
		System.out.println("===HomeController.toPublicSharingFile()===");
		File[] fileArray = null;
		try {
			fileArray = homeService.getFileArray(request, openPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("fileArray", fileArray);
		model.addAttribute("size", fileArray.length);
		
		model.addAttribute("msg", "");
		
		return "publicSharingFolder";
	}

	/*
	 * 跳转 我的共享文件夹
	 */
	@RequestMapping("/toMeSharingFolder")
	public String toMeSharingFolder(Model model, HttpServletRequest request) {
		System.out.println("===HomeController.toMeSharingFolder()===");
		// 获取共享文件夹目标
		try {
			if (request.getSession().getAttribute("visitorIp").equals("192.168.1.17")) {

				File[] fileArray = homeService.getFileArray(request, null);
				model.addAttribute("fileArray", fileArray);
				model.addAttribute("size", fileArray.length);
			} else { // 不是本机
				List<SmbFile> files = homeService.getFileListBySmb(request);
				model.addAttribute("fileArray", files);
				model.addAttribute("size", files.size());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "meSharingFolder";
	}

	/*
	 * 跳转 文件传输
	 */
	@RequestMapping("/toFileTransfer")
	private String toFileTransfer() {
		System.out.println("===HomeController.toFileTransfer()===");
		
		return "fileTransfer";
	}

	/*
	 * 上传到"公用共享文件夹"
	 */
	@RequestMapping("/upLoad")
	public String upLoad(HttpServletRequest request, String srcPath, String fileName, String destPath, Model model) {
		System.out.println("===HomeController.upLoad()===");
		
		//1.上传部分
		int flag = -1;
		try {
			flag = homeService.upLoad(request, srcPath, fileName, destPath); //1:上传成功（文件数+1） 2：上传成功（文件数不变） 0：上传失败
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.跳转页面
		File[] fileArray = null;
		try {
			fileArray = homeService.getFileArray(request, destPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("msg", flag);
		model.addAttribute("fileArray", fileArray);
		model.addAttribute("size", fileArray.length);		
		
		return "publicSharingFolder";
	}

	/*
	 * 新建目录
	 */
	@RequestMapping("/createFolder")
	@ResponseBody
	public String createFolder(String type, String fileName, String currentPath) {
		Map map = new HashMap();
		int flag = 0;
		try {

			String path = currentPath + fileName;
			System.out.println(path);
			flag = homeService.createFolder(path, type);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag == 1) {
			map.put("msg", "在当前路径下创建目录成功！");
		} else if (flag == 0) {
			map.put("msg", "在当前路径下创建目录失败！");
		} else if (flag == -1) {
			map.put("msg", "在当前路径下创建目录失败！新建文件名重名");
		}
		// 把map转成 json,然后再前台接收
		jsonData = JSONObject.fromObject(map);
		// System.out.println("最后转成的json为："+jsonData);
		return jsonData.toString();

	}
	
	
	/*
	 * 跳转 信息检索
	 */
	@RequestMapping("/toQuery")
	public String toQuery(Model model,String path,String searchInfo,String type) {
		System.out.println("===toQuery()===");
		
		if (path.indexOf("smb")==-1) {//检索服务器
			try {
				List<FileInfo> fileInfos = new ArrayList<>();
				fileInfos = homeService.getListByInfo(path, searchInfo);
				model.addAttribute("fileArray", fileInfos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {//检索访问者本机
			try {
				List<FileInfo> file = new ArrayList<>();
				file = homeService.getSmbListByInfo(path, searchInfo);
				model.addAttribute("fileArray", file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		model.addAttribute("type", type);
		
		return "query";
	}
}
