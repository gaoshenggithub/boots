package com.fast.img;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args) throws IOException {

		String fileContent = readFileContent("");

		System.out.println(fileContent);
	}

	//参数string为你的文件名
	public  static String readFileContent(String fileName) throws IOException {

		File file = new File(fileName);

		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String s = null;
		StringBuffer sb = new StringBuffer();
		while((s = buffer.readLine())!=null){//使用readLine方法，一次读一行
			sb.append(s.trim());
		}

		String xml = buffer.toString();
		return xml;
	}
}