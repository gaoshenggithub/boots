package com.ftp.config;

import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;

public class FtpTest {

	public void testFtp() throws Exception {
		// 1、连接ftp服务器
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("47.105.177.77", 21);
		// 2、登录ftp服务器
		ftpClient.login("ftpuser", "123456");
		String str="D:\\OpenSources\\1.png";
		String location=str.replace("\\\\", "/");
		// 3、读取本地文件
		FileInputStream inputStream = new FileInputStream(location);
		// 4、上传文件
		// 1）指定上传目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/www");
		// 2）指定文件类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		// 第一个参数：文件在远程服务器的名称
		// 第二个参数：文件流
		ftpClient.storeFile("hello.jpg", inputStream);
		// 5、退出登录
		ftpClient.logout();
	}
}
