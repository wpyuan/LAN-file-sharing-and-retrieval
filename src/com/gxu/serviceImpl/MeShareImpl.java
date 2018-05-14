package com.gxu.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxu.service.MeShareService;
import com.gxu.utils.CodeChangeUtil;

import jcifs.smb.SmbFile;
@Service
@Transactional
public class MeShareImpl implements MeShareService {

	private CodeChangeUtil codeChangUtil;
	
	@Override
	public List<SmbFile> getClientFileArrayBySmb(String url)throws Exception {
		url = codeChangUtil.changeUTF8(url);
		System.out.println("url="+url);
		SmbFile file = new SmbFile(url);
		SmbFile[] files = file.listFiles();
		List<SmbFile> fileArray = new ArrayList<>();
		for (SmbFile smbFile : files) {
			if(!smbFile.isHidden()) {
				fileArray.add(smbFile);
				System.out.println("no hidden: "+smbFile.getName());
			}else {
				System.out.println("hidden: "+smbFile.getName());
			}
		}
		return fileArray;
		//return file.listFiles();
	}

}
