package com.gxu.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.gxu.entity.FileInfo;
import com.gxu.service.HomeService;
import com.gxu.test.GetAllFile;
import com.gxu.utils.CodeChangeUtil;
import com.gxu.utils.FilenameFilterUtil;
import com.gxu.utils.SearchInfoUtil;
import com.gxu.utils.ShareFolder;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

@Service
@Transactional
public class HomeImpl implements HomeService {

	private CodeChangeUtil codeChangeUtil;
	private ShareFolder shareFolder;
	private SearchInfoUtil searchInfoUtil = new SearchInfoUtil();
	protected static Logger log = Logger.getLogger(HomeImpl.class);
	@Override
	public void downLoad(HttpServletRequest request, HttpServletResponse response, String path, String name)
			throws Exception {
		
		path = codeChangeUtil.changeUTF8(path);
		//log.info("文件名：" + name);
		//log.info("路径：" + path);
		
		response.setHeader("Content-disposition", "attachment;filename=" + name);
		//log.info("文件名：" + URLEncoder.encode(name, "UTF-8"));
		InputStream is = new FileInputStream(path);
		OutputStream out = response.getOutputStream();
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = is.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		out.close();
		is.close();

	}

	@Override
	public int upLoad(HttpServletRequest request, String srcPath, String fileName, String destPath) throws Exception {
		int flag = -1;
		srcPath = codeChangeUtil.changeUTF8(srcPath);
		fileName = codeChangeUtil.changeUTF8(fileName);
		String targetIp = (String) request.getSession().getAttribute("visitorIp");

		if (targetIp.equals("192.168.1.17")) {

			// destPath = "\\\\"+targetIp+"\\fileGX\\"+fileName; 
			destPath += fileName; 
			flag = shareFolder.copy(srcPath, fileName, destPath);
		}
		else {
			// File dest = new File("\\\\"+targetIp+"\\fileGX\\"+fileName);
			srcPath = "\\\\" + targetIp + "\\Users\\fileGX\\" + fileName;
			// String destPath = "\\\\" + "192.168.1.17" + "\\fileGX\\" + fileName;
			destPath += fileName; 
			flag = shareFolder.copy(srcPath, fileName, destPath);
		}
		return flag ;
	}

	@Override
	public File[] getFileArray(HttpServletRequest request, String openPath) throws Exception {
		System.out.println("---HomeImpl.getFileArray()---");
		String path = null;
		openPath=codeChangeUtil.changeUTF8(openPath);
		System.out.println("openPath="+openPath);
		if (openPath == null) {
			path = "C:/Users/fileGX/";
		} else {
			path = openPath;
		}
		
		File file = new File(path);
		return file.listFiles();
	}

	@Override
	public List<SmbFile> getFileListBySmb(HttpServletRequest request) throws Exception {
		String url = "smb://" + request.getSession().getAttribute("visitorIp") + "/";
		return shareFolder.getShareFolder(url);
	}

	@Override
	public int createFolder(String path, String type) throws Exception {
		if(type.equals("1")) {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs(); 
			}else {
				return -1;
			}
			file = new File(path);
			if(file.exists()&&file.isDirectory()) {
				System.out.println("create success");
				return 1;
			}else {
				System.out.println("create faild");
				return 0;
			}
		}else if (type.equals("2")) {
			try {
				SmbFile file = new SmbFile(path);

				if (!file.exists()) {
					file.mkdirs(); 
				} else {
					return -1;
				}
				file = new SmbFile(path);
				if (file.exists() && file.isDirectory()) {
					System.out.println("create success");
					return 1;
				} else {
					System.out.println("create faild");
					return 0;
				}
				// file.createNewFile(); 
			} catch (SmbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			return 0;
		}
		return 0;
	}

	@Override
	public int upLoadByHTTP() throws Exception {
		
		return 0;
	}

	@Override
	public List<FileInfo> getListByInfo(String path, String searchInfo) throws Exception {
//		System.out.println("path="+path);
//		System.out.println("searchInfo="+searchInfo);
		searchInfo = codeChangeUtil.changeUTF8(searchInfo);
		path = codeChangeUtil.changeUTF8(path);
//		System.out.println("path="+path);
//		System.out.println("searchInfo="+searchInfo);
		List<File> file = searchInfoUtil.run(path, searchInfo);
		FileInfo f = null;
		List<FileInfo> fileInfos = new ArrayList<>();
		for (File file2 : file) {
			f = new FileInfo();
			f.setLastModified(file2.lastModified());
			f.setName(file2.getName());
			f.setPath(file2.getPath().replaceAll("\\\\", "/"));
			f.setDirectory(file2.isDirectory()); 
			f.setKeyWord(file2.getName().indexOf(searchInfo));
			f.setKeyWordLength(searchInfo.length());
			fileInfos.add(f);
		}	
		return fileInfos;
	}

	@Override
	public List<FileInfo> getSmbListByInfo(String path, String searchInfo) throws Exception {
		searchInfo = codeChangeUtil.changeUTF8(searchInfo);
		path = codeChangeUtil.changeUTF8(path);
		List<SmbFile> file = searchInfoUtil.smbRun(path, searchInfo);
		FileInfo f = null;
		List<FileInfo> fileInfos = new ArrayList<>();
		for (SmbFile file2 : file) {
			f = new FileInfo();
			f.setLastModified(file2.lastModified());
			f.setName(file2.getName());
			f.setPath(file2.getPath().replaceAll("\\\\", "/"));
			f.setDirectory(file2.isDirectory()); 
			f.setKeyWord(file2.getName().indexOf(searchInfo));
			f.setKeyWordLength(searchInfo.length());
			fileInfos.add(f);
		}	
		return fileInfos;
	}

}
