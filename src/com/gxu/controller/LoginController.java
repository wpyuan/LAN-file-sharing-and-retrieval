package com.gxu.controller;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxu.service.LoginService;

import jcifs.smb.SmbFile;

@Controller
public class LoginController {

	private LoginService loginService;
	
	public LoginService getLoginService() {
		return loginService;
	}

	@Resource
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


	@RequestMapping("/createLocalShareFolder")
	@ResponseBody
	public String createLocalShareFolder(HttpServletRequest request) {
		System.out.println("===LoginController.createLocalShareFolder()===");
		try {
			return loginService.createShareFolder(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error:"+e.getMessage();
		}
		
	}

	
	@RequestMapping("/toHome")
	public String toHome(Model model, HttpServletRequest request) {
		System.out.println("===LoginController.toHome()===");

		//String path = "C:/Users/fileGX/";
		String path = "C:/Users/fileGX/";
		File[] fileArray = null;
		try {
			fileArray = loginService.getFileArray(path, request);
			System.out.println("-----------------------");
			for (File file : fileArray) {
				System.out.println(file.getName());
			}
			System.out.println("-----------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("currentPath", path);
		model.addAttribute("size", fileArray.length);
		model.addAttribute("visitorIp", request.getSession().getAttribute("visitorIp"));

		return "home";
	}

	/*
	 * 获取局域网内ip
	 */
	@RequestMapping("/toLogin")
	public String GetIp(HttpServletRequest request, Model model) {
		System.out.println("===LoginController.GetIp()===");

		String visitorIp = null;
		try {
			visitorIp = loginService.getVisitorIp(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session;
		session = request.getSession();
		session.setAttribute("visitorIp", visitorIp);
		model.addAttribute("userIp", visitorIp);
		return "login";
	}

}
