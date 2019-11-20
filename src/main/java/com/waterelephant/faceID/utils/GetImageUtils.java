package com.waterelephant.faceID.utils;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
//import java.io.FileOutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.mysql.jdbc.StringUtils;

/**
 * 图片转换工具类
 * @author dengyan
 *
 */
public class GetImageUtils {

	private static Logger logger = LoggerFactory.getLogger(GetImageUtils.class);

	/**
	 * 通过http下载图片并将图片保存本地
	 * 
	 * @param fileUrl
	 * @param savePath
	 * @return
	 */
	public static boolean saveUrlAs(String fileUrl, String savePath,
			String fileName) {
		/* fileUrl网络资源地址 */
		HttpURLConnection connection = null;
		try {
			File file = new File(savePath);
			if (!file.exists() || !file.isDirectory()) {
				file.mkdirs();
			}
			URL url = new URL(fileUrl);/* 将网络资源地址传给,即赋值给url */
			/* 此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流 */
			connection = (HttpURLConnection) url.openConnection();
			logger.info("阿里云图片地址"+ fileUrl +"访问code:" + String.valueOf(connection.getResponseCode()));
			if (connection.getResponseCode() == 200) {
				DataInputStream in = new DataInputStream(connection.getInputStream());
				/* 此处也可用BufferedInputStream与BufferedOutputStream */
				DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath + "/" + fileName));
				/* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
				byte[] buffer = new byte[4096];
				int count = 0;
				while ((count = in.read(buffer)) > 0)/* 将输入流以字节的形式读取并写入buffer中 */
				{
					out.write(buffer, 0, count);
				}
				out.close();/* 后面三行为关闭输入输出流以及网络资源的固定格式 */
				in.close();
				return true;/* 网络资源截取并存储本地成功返回true */
			}else {
				logger.info("获取图片地址无法访问，请检查图片地址是否存在图片，地址为：" + fileUrl);
				return false;
			}
		} catch (Exception e) {
			logger.error("存储图片异常：" + fileUrl + "," + savePath + "," + fileName,e);
			return false;
		}finally {
			if (connection != null) {
				try {
					connection.disconnect();
				}catch (Exception e) {
					logger.error("关闭HttpURLConnection异常：" + connection);
				}
			}
		}
	}

	/**
	 * base64解码并保存图片到本地
	 * 
	 * @param imgStr
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static boolean base64SaveImage(String imgStr, String filePath,
			String fileName) {
		if (StringUtils.isNullOrEmpty(imgStr)) {
			return false;
		}

		File file = new File(filePath);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}

		BASE64Decoder decoder = new BASE64Decoder();

		try {
			// base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			String imagFilePath = filePath + "/" + fileName;
			FileOutputStream out = new FileOutputStream(imagFilePath);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.info("文件解析异常：" + e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * 文件直接保存本地
	 * 
	 * @param mutfile
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static boolean saveFile(MultipartFile mutfile, String filePath,
			String fileName) {
		// 第一步：验证文件是否为空
		if (mutfile == null) {
			return false;
		}

		// 第二步：验证保存路径是否存在，不存在则创建路径
		File file = new File(filePath);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		try {
			// 第三步：保存文件到本地
			mutfile.transferTo(new File(filePath + "/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("保存视频文件到本地成功！路径地址：" + filePath + "/" + fileName);
		return true;
	}

	/**
	 * 删除本地缓存的图片
	 * 
	 * @param imagePath
	 * @return
	 */
	public static boolean delImage(String imagePath) {
		boolean bo = false;
		File file = new File(imagePath);
		if (file.exists()) {// 判断文件路径是否存在
			file.delete();
			bo = true;
		}
		logger.info("删除图片成功！");
		return bo;
	}

	/**
	 * 二进制转文件
	 * 
	 * @param imageByte
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static boolean byteToFile(String imageByte, String filePath,
			String fileName) {
		if (StringUtils.isNullOrEmpty(imageByte)) {
			return false;
		}

		File file = new File(filePath);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}

		byte[] b = new byte[20971520];

		try {
			FileOutputStream out = new FileOutputStream(filePath);
			InputStream ins = new FileInputStream(imageByte);
			int size = 0;
			while ((size = ins.read(b)) != -1) {
				logger.info("输入流大小：" + size);
				out.write(b, 0, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("二进制转文件出现异常：" + e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * 文件转base64
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String encodeBase64File(String path) throws Exception {  
	   File file = new File(path);  
	   FileInputStream inputFile = new FileInputStream(file);  
	   byte[] buffer = new byte[(int) file.length()];  
	   inputFile.read(buffer);  
	   inputFile.close();  
	   return new BASE64Encoder().encode(buffer);  
		  
	} 
}
