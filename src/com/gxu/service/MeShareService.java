package com.gxu.service;

import java.io.File;
import java.util.List;

import jcifs.smb.SmbFile;

public interface MeShareService {
	public List<SmbFile> getClientFileArrayBySmb(String url)throws Exception;
}
