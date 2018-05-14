package com.gxu.controller;

import java.io.File;
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
import com.gxu.service.TransferService;

import jcifs.smb.SmbFile;
import net.sf.json.JSONObject;

@Controller
public class TransferController {
	private JSONObject jsonData;
	private TransferService tService;
	private HomeService homeService;
	private MeShareService meShareService;
	
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

	public JSONObject getJsonData() {
		return jsonData;
	}

	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}

	public TransferService gettService() {
		return tService;
	}

	@Resource
	public void settService(TransferService tService) {
		this.tService = tService;
	}

	/*
	 * ²âÊÔÁ¬½Ó
	 */
	@RequestMapping("/testLink")
	@ResponseBody
	public String testLink(String receiveIP, HttpServletRequest request) {
		System.out.println("===TransferController.testLink()===");
		int flag = -1;
		try {
			flag = tService.testlinkByIP(request, receiveIP);
			if (flag == 1)
				return "success";
			else
				return "error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error:" + e.getMessage();
		}

	}

	/*
	 * ´«ÊäÎÄ¼þ
	 */
	@RequestMapping("/transfer")
	@ResponseBody
	public String transfer(String receiveIP,String path,String fileName,HttpServletRequest request) {
		System.out.println("===TransferController.transfer()===");
		Map map = new HashMap();
		try {
			if(tService.transfer(request, receiveIP,path,fileName)) {
				System.out.println("transfer success");
				map.put("msg", "transfer success");
			}else map.put("msg", "transfer error");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "error:" + e.getMessage());
		}
		// ï¿½ï¿½map×ªï¿½ï¿½ json,È»ï¿½ï¿½ï¿½ï¿½Ç°Ì¨ï¿½ï¿½ï¿½ï¿½
		jsonData = JSONObject.fromObject(map);
		// System.out.println("ï¿½ï¿½ï¿½×ªï¿½Éµï¿½jsonÎªï¿½ï¿½"+jsonData);
		return jsonData.toString();
	}
	
	/*
	 * step2  ÏÔÊ¾¹²ÏíÎÄ¼þ¼Ð
	 */
	@RequestMapping("/showShareFolder")
	public String showShareFolder(Model model,HttpServletRequest request) {
		System.out.println("===TransferController.showShareFolder()===");
		
		try {
			if (request.getSession().getAttribute("visitorIp").equals("192.168.1.17")) {

				File[] fileArray = homeService.getFileArray(request, null);
				model.addAttribute("fileArray", fileArray);
				model.addAttribute("size", fileArray.length);
			} else { // ¿Í»§¶Ë
				List<SmbFile> files = homeService.getFileListBySmb(request);
				model.addAttribute("fileArray", files);
				model.addAttribute("size", files.size());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "step2";
	}
	
	/*
	 * step2 open´ò¿ª¹²ÏíÎÄ¼þ¼Ð
	 */
	@RequestMapping("/toOpenStep2")
	public String toOpenStep2(Model model, String openPath, HttpServletRequest request) {

		System.out.println("===TransferController.toOpenStep2()===");
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
		else { // ¿Í»§¶Ë
			List<SmbFile> files = null;
			try {
				files = meShareService.getClientFileArrayBySmb(openPath);
	
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			model.addAttribute("fileArray", files);
			model.addAttribute("size", files.size());
		} 
		return "step2";
	}
	
}
