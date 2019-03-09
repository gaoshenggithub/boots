package com.fast.cro;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarCode {
	/**
	 * 条形码编码
	 **/
	public void encode(String contents, int width, int height, String imgPath) {
		int codeWidth = 3 + (7 * 6) + 5 + (7 * 6) + 3;
		codeWidth = Math.max(codeWidth, width);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.EAN_13, codeWidth, height, null);
			MatrixToImageWriter
					.writeToFile(bitMatrix, "png", new File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 条形码解码
	 **/
	public String decode(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			result = new MultiFormatReader().decode(bitmap, null);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		/** 编码 **/
		String imgPath = "C:\\Users\\89155\\Desktop\\fast\\zxing_EAN13.png";
		// 益达无糖口香糖的条形码
		String contents = "6923450657713";


		int width = 105, height = 50;
		BarCode barCode = new BarCode();
		barCode.encode(contents, width, height, imgPath);
	}
}
