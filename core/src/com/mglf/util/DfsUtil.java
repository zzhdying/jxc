package com.mglf.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.mglf.dao.interceptor.DbInterceptor;

/**
 * 分布式文件存储工具类
 * 
 * @author zhongzhuohan
 * 
 */
public class DfsUtil {

	private final static Log log = LogFactory.getLog(DfsUtil.class);
	
	private static class Dfs {
		public TrackerServer trackerServer;
		public StorageClient1 storageClient;
	}

	private static TrackerClient trackerClient = null;

	public static Dfs getDfs() throws Exception {
		Dfs ret = new Dfs();
		if (trackerClient == null) {
			String classPath = new File(DfsUtil.class.getResource("/")
					.getFile()).getCanonicalPath();
			String configFilePath = classPath + File.separator
					+ "config/fdfs_client.conf";
			ClientGlobal.init(configFilePath);
			trackerClient = new TrackerClient();
		}

		ret.trackerServer = trackerClient.getConnection();
		ret.storageClient = new StorageClient1(ret.trackerServer, null);

		return ret;
	}

	public static void putDfs(Dfs dfs) throws Exception {
		if (dfs == null || dfs.trackerServer == null) {
			return;
		}
		dfs.trackerServer.close();
	}

	/**
	 * 下载远程文件
	 * 
	 * @param remoteFilename
	 *            远程文件名
	 * @param path
	 *            本地文件保存路径
	 * @throws Exception
	 */
	public static void downloadFile(String remoteFilename, String path)
			throws Exception {
		Dfs dfs = getDfs();
		try {
			dfs.storageClient.download_file1(remoteFilename, path);
		} finally {
			putDfs(dfs);
		}
	}

	/**
	 * 下载远程文件
	 * 
	 * @param remoteFilename
	 *            远程文件名
	 * @return 远程文件数据
	 * @throws Exception
	 */
	public static byte[] downloadFile(String remoteFilename) throws Exception {
		Dfs dfs = getDfs();
		try {
			byte[] buf = dfs.storageClient.download_file1(remoteFilename);
			return buf;
		} catch (Exception e){
			log.error("downloadFile", e);
			return null;
		} finally {
			putDfs(dfs);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param path
	 *            本地文件
	 * @return 远程文件名
	 * @throws Exception
	 */
	public static String uploadFile(String path) throws Exception {
		File file = new File(path);
		String name = file.getName();

		return uploadFile(path, name);
	}

	/**
	 * 上传文件
	 * 
	 * @param path
	 *            本地文件
	 * @return 远程文件名
	 * @throws Exception
	 */
	public static String uploadFile(String path, String name) throws Exception {
		String extName = "";

		if (name.lastIndexOf('.') < name.length()) {
			extName = name.substring(name.lastIndexOf('.') + 1);
		}

		Dfs dfs = getDfs();

		try {
			NameValuePair[] meta_list = new NameValuePair[1];
			meta_list[0] = new NameValuePair("fileFullName", name);
			String remoteFilename = dfs.storageClient.upload_file1(path,
					extName, meta_list);

			return remoteFilename;
		} finally {
			putDfs(dfs);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param fileName
	 *            原始文件名
	 * @param fileExtName
	 *            原始文件扩展名
	 * @param dat
	 *            原始文件数据
	 * @return 远程文件名
	 * @throws Exception
	 */
	public static String uploadData(String fileName, byte[] dat)
			throws Exception {
		Dfs dfs = getDfs();
		try {
			String extName = "";

			if (fileName.lastIndexOf('.') < fileName.length()) {
				extName = fileName.substring(fileName.lastIndexOf('.') + 1);
			}

			NameValuePair[] meta_list = new NameValuePair[1];
			meta_list[0] = new NameValuePair("fileFullName", fileName);
			String remoteFilename = dfs.storageClient.upload_file1(dat,
					extName, meta_list);

			return remoteFilename;
		} finally {
			putDfs(dfs);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param fileName
	 *            原始文件名
	 * @param fileExtName
	 *            原始文件扩展名
	 * @param dat
	 *            原始文件数据
	 * @return 远程文件名
	 * @throws Exception
	 */
	public static String uploadData(String fileName, InputStream is)
			throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;

		while ((len = is.read(buf)) > 0) {
			baos.write(buf, 0, len);
		}

		is.close();
		baos.close();

		return uploadData(fileName, baos.toByteArray());
	}

	/**
	 * 删除远程文件
	 * 
	 * @param remoteFilename
	 *            远程文件名
	 * @throws Exception
	 */
	public static void deleteFile(String remoteFilename) throws Exception {
		Dfs dfs = getDfs();
		try {
			dfs.storageClient.delete_file1(remoteFilename);
		} finally {
			putDfs(dfs);
		}
	}

	// test
	public static void main(String[] argvs) {
		System.out.println("DfsUtil Test");

		// try {
		// String fileName = "fileName1";
		// String fileExtName = "test";
		// byte[] dat = new byte[100];
		//
		// String remoteFilename = uploadData(fileName, fileExtName, dat);
		//
		// dat = downloadFile(remoteFilename);
		// System.out.println("dat.length:"+dat.length);
		//
		// deleteFile(remoteFilename);
		// dat = downloadFile(remoteFilename);
		// System.out.println("dat.length:"+dat);
		//
		// }catch(Exception e){
		// e.printStackTrace();
		// }

	}
}
