package com.gxu.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxu.service.MeShareService;
import com.gxu.utils.CodeChangeUtil;
import com.gxu.utils.ShareFolder;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import net.sf.json.JSONObject;

/**
 * ���ù����ļ��п��Ʋ�
 */
@Controller
public class PublicController {

	private CodeChangeUtil changeUtil;
	
	private ShareFolder shareFolder;

	private JSONObject jsonData;

	private MeShareService meShareService;
	
	public MeShareService getMeShareService() {
		return meShareService;
	}
	@Resource
	public void setMeShareService(MeShareService meShareService) {
		this.meShareService = meShareService;
	}

	public JSONObject getJsonData() {
		return jsonData;
	}

	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}

	/*
	 * �����ļ����ļ�������
	 */
	@RequestMapping("/fileOrFolderNumber")
	@ResponseBody
	public String fileOrFolderNumber(String type, String openPath, HttpServletRequest request) {
		System.out.println("===PublicController.fileOrFolderNumber()===");
		openPath = changeUtil.changeUTF8(openPath);
		Map map = new HashMap();
		if (type.equals("1")) {
			System.out.println("���ù����ļ��м��� openPath="+openPath);
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
			System.out.println("�ҵĹ����ļ��м��� openPath="+openPath);
			try {
				if (!request.getSession().getAttribute("visitorIp").equals("192.168.1.17")) { 
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
