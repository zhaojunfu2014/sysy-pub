package com.zjf.cms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjf.cms.dao.AttachmentDao;

/**
 * �����ļ�����
 * @author �Կ���
 *
 * Aug 23, 2012
 */
public class ConfigUtil {
	private Properties prop;
	private Log log = LogFactory.getLog(getClass());
	/**
	 * ���췽��
	 */
	public ConfigUtil() {
		prop = new Properties();
		try {
//			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			URL url = getClass().getClassLoader().getResource("config.properties");
			File f = new File(url.getPath());
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ��ȡ�ַ���ֵ
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		return (String) prop.get(key);
	}
	/**
	 * ��ȡintֵ
	 * @param key
	 * @return
	 */
	public int getIntValue(String key){
		String v = (String) prop.get(key);
		int value = Integer.parseInt(v);
		return value;
	}
	/**
	 * ��ȡ�����ϴ�������
	 * @return
	 */
	public List<String> getUploadType(){
		String value = prop.getProperty("uploadType");
		String[] values = value.split(";");
		List<String> types = new ArrayList<String>();
		for(String v:values){
			types.add(v);
			
		}
		log.debug(values);
		return types;
	}
	public void setValue(String key,String value){
		prop.setProperty(key, value);
	}
	public void commit(){
		FileOutputStream fos;
		
		try {
			File f = new File(ConfigUtil.class.getClassLoader().getResource("config.properties").toURI());
			fos = new FileOutputStream(f);
			prop.store(fos, "");
			fos.flush();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
