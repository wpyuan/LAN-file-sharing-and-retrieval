package com.gxu.utils;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterUtil implements FilenameFilter {

	String searchInfo = null;
    public FilenameFilterUtil(String searchInfo){
       this.searchInfo =searchInfo;
    }
	@Override
	public boolean accept(File dir, String name) {
		if (name.indexOf(searchInfo)==-1) {
			return false;
		} else {
			return true;
		}
	}

}
