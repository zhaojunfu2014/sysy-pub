package com.zjf.cms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * �ϴ�����
 * @author �Կ���
 *
 * Aug 22, 2012
 */
public class UploadUtil {
	private MultipartFile u;
	private String path;
	private String vPath;
	private HttpServletRequest req;
	/**
	 * �����ļ�ϵͳ
	 * @return
	 * @throws IOException
	 */
	public  String saveToFileSystem() throws IOException{
		File target = new File(path);
		if(!target.getParentFile().exists()){
			target.getParentFile().mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(target);
		fos.write(u.getBytes());
		fos.flush();
		fos.close();
		return vPath;
	}
	/**
	 * �ϴ�����-���췽��
	 * @param u
	 * @param req
	 * @param dir
	 */
	public UploadUtil(MultipartFile u,HttpServletRequest req,String dir) {
		this.u = u;
		if(u!=null){
			String vPath = "resources"+"/"+dir+"/";
			String ext = u.getOriginalFilename().substring(u.getOriginalFilename().lastIndexOf('.'));
			UUID uuid =UUID.randomUUID();
			String fileName = uuid.toString()+"";
			vPath +=fileName+ext;
			String realPath = req.getRealPath(vPath);
			this.vPath = vPath;
			this.path = realPath;
		}
		this.req = req;
	}
	/**
	 * ���ļ�ϵͳ��ɾ������
	 * @param path
	 */
	public void deleteFromFileSystem(String path){
		path = req.getRealPath(path);
		File f = new File(path);
		if(f.exists()){
			f.delete();
		}
	}
}
