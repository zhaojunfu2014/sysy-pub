package com.zjf.cms.controllor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 验证码servlet
 * @author 赵俊夫
 *
 * Aug 29, 2012
 */
public class CheckcodeServlet extends HttpServlet {

	private int width;
	private int height;
	private int number; //显示多少个字符
	private String codes; //有哪些字符可供选择
	private Log log = LogFactory.getLog(getClass());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		width = Integer.parseInt(config.getInitParameter("width"));
		height = Integer.parseInt(config.getInitParameter("height"));
		number = Integer.parseInt(config.getInitParameter("number"));
		codes = config.getInitParameter("codes");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		
		
		//创建一张图片
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		
		//创建白色背景
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		//画黑边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		
		Random random = new Random();
		
		//每个字符占据的宽度
		int x = (width - 1) / number;
		int y = height -4;
		String checkcode="";
		//随机生成字符
		for(int i=0; i<number; i++){
			String code = String.valueOf( codes.charAt( random.nextInt(codes.length())) );
			checkcode+=code;
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red,green,blue));
			
			Font font = new Font("Arial",Font.PLAIN,random(height/2,height));
			g.setFont(font);
			
			g.drawString(code, i * x + 1, y);
		}
		
		HttpSession session = request.getSession();
		log.debug(checkcode);
		session.setAttribute("checkcode", checkcode);
		
		OutputStream out = response.getOutputStream();
		//将图片转换为JPEG类型
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);

		out.flush();
		out.close();
		
	}
	
	/**
	 * 产生一个从min到max之间的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	private int random(int min,int max){
		int m = new Random().nextInt(999999) % (max - min);
		return m + min;
	}

}
