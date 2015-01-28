package com.mglf.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class ImageMergeUtil {

	private static final String API_URL = "https://api.tinypng.com/shrink";

	public static void tinyPng(MergeItem imageItem) throws Exception {
		final String key = "sDPdVN-WFxV1HCs61U1Y5kzLrx0x772d";

		HttpURLConnection connection = (HttpURLConnection) new URL(API_URL)
				.openConnection();
		String auth = DatatypeConverter.printBase64Binary(("api:" + key)
				.getBytes("UTF-8"));
		connection.setRequestProperty("Authorization", "Basic " + auth);
		connection.setDoOutput(true);

		OutputStream request = connection.getOutputStream();
		request.write(imageItem.imgData);
		
		if (connection.getResponseCode() == 201) {
			// Compression was successful, retrieve output from Location header.
			final String url = connection.getHeaderFields().get("Location")
					.get(0);
			connection = (HttpURLConnection) new URL(url).openConnection();
			InputStream response = connection.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			byte[] buf=new byte[1024];
			int len;
			
			while((len=response.read(buf))>0){
				baos.write(buf, 0, len);
			}
			
			baos.close();
			
			imageItem.imgData = baos.toByteArray();
		} else {
			System.out.println("Compression failed.");
		}
	}

	public static class ImageItem {
		public String name;
		public byte[] data;
	}

	public static class MergeItem {
		public String css;
		public byte[] imgData;
	}

	public static MergeItem merge(List<ImageItem> list) throws Exception {
		MergeItem ret = new MergeItem();
		String css = "";

		Image[] images = new Image[list.size()];
		int height = 0;
		int width = 0;
		for (int i = 0; i < list.size(); i++) {
			ImageItem item = list.get(i);

			ByteArrayInputStream bais = new ByteArrayInputStream(item.data);
			Image image = ImageIO.read(bais);

			images[i] = image;

			if (image.getWidth(null) > width) {
				width = image.getWidth(null);
			}
			height += image.getHeight(null);

			if (item.name.indexOf('.') > 0) {
				item.name = item.name.substring(0, item.name.indexOf('.'));
			}
			item.name = item.name.replaceAll("\\.", "_");
		}

		BufferedImage mergeImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = mergeImage.createGraphics();

		int y = 0;
		for (int i = 0; i < list.size(); i++) {
			int w = images[i].getWidth(null);
			int h = images[i].getHeight(null);
			g.drawImage(images[i], 0, y, w, h, null);

			String s = ".mergeimg-" + list.get(i).name
					+ "{ background-position: 0 " + (y > 0 ? "-" : "") + y
					+ "; width: " + w + "px; height: " + h + "px; }";
			css += s;
			css += "\n";

			y += images[i].getHeight(null);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(mergeImage, "png", baos);
		baos.close();

		ret.imgData = baos.toByteArray();
		ret.css = css;

		tinyPng(ret);
		
		return ret;
	}

	public static List<ImageItem> readFiles(String path) throws Exception {
		File dir = new File(path);

		List<ImageItem> list = new ArrayList<ImageItem>();

		for (String fileNmae : dir.list()) {
			File file = new File(dir.getAbsolutePath() + File.separator
					+ fileNmae);
			ImageItem item = new ImageItem();
			item.name = file.getName();
			item.data = new byte[(int) file.length()];

			FileInputStream fis = new FileInputStream(file);
			fis.read(item.data);
			fis.close();

			list.add(item);
		}

		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			List<ImageItem> list = readFiles("C:/Users/Administrator/Desktop/test/a");

			MergeItem item = merge(list);

			File outFile = new File("F:/temp/a.png");
			FileOutputStream fos = new FileOutputStream(outFile);
			fos.write(item.imgData);
			fos.close();

			System.out.println(item.css);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
