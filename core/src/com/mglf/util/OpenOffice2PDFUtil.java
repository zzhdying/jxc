package com.mglf.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.mglf.util.ConfigUtil;
import com.mglf.util.EmptyUtil;
import com.mglf.util.Mht2HtmlUtil;
import com.mglf.util.ConfigUtil;
import com.mglf.util.EmptyUtil;
import com.mglf.util.Mht2HtmlUtil;

/**
 * 使用openoffice接口将文档转成PDF
 * 
 * @author jinxiaochen
 * 
 */
public class OpenOffice2PDFUtil {

	// 定义转化的源文件类型
	final static private String FILE_TYPE_DOC = "doc";
	final static private String FILE_TYPE_DOCX = "docx";
	final static private String FILE_TYPE_HTML = "html";
	final static private String FILE_TYPE_HTM = "htm";
	final static private String FILE_TYPE_XLS = "xls";
	final static private String FILE_TYPE_XLSX = "xlsx";
	// 定义转化的目标类型
	final static private String FILE_TYPE_PDF = "pdf";

	/**
	 * 
	 * @param fileType
	 *            需要转换的文件类型
	 * @param is
	 *            文件的输入流
	 * @return
	 */
	public synchronized static byte[] office2PDF(String fileType, InputStream is)
			throws Exception {

		OpenOfficeConnection connection = null;

		if (EmptyUtil.isEmpty(is)) {
			return null;
		}
		InputStream mth_is = null;
		// 注册文件类型
		DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
		// 生成的文件类型FORMAT-pdf
		DocumentFormat df = null;
		if (FILE_TYPE_DOC.equalsIgnoreCase(fileType)) {
			// 如果是DOC，识别此DOC是伪DOC还是MHT格式的DOC
			mth_is = Mht2HtmlUtil.mht2html(is, "");
			// 如果是MHT格式的DOC则对进行解析
			if (!EmptyUtil.isEmpty(mth_is)) {
				is = mth_is;
				df = formatReg.getFormatByFileExtension(FILE_TYPE_HTML);
			} else {
				df = formatReg.getFormatByFileExtension(FILE_TYPE_DOC);
			}
		} else if (FILE_TYPE_DOCX.equalsIgnoreCase(fileType)) {
			df = formatReg.getFormatByFileExtension(FILE_TYPE_DOCX);
		} else if (FILE_TYPE_HTML.equalsIgnoreCase(fileType)) {
			df = formatReg.getFormatByFileExtension(FILE_TYPE_HTML);
		} else if (FILE_TYPE_HTM.equalsIgnoreCase(fileType)) {
			df = formatReg.getFormatByFileExtension(FILE_TYPE_HTML);
		} else if (FILE_TYPE_XLS.equalsIgnoreCase(fileType)) {
			df = formatReg.getFormatByFileExtension(FILE_TYPE_XLS);
		} else if (FILE_TYPE_XLSX.equalsIgnoreCase(FILE_TYPE_XLSX)) {
			df = formatReg.getFormatByFileExtension(FILE_TYPE_XLSX);
		} else {
			return null;
		}

		// 获取IP和端口号
		String openofficeIp = ConfigUtil.readSysValue("openoffice_ip");
		String openofficePort = ConfigUtil.readSysValue("openoffice_port");

		// OpenOffice环境变量home
		String OpenOffice_HOME = ConfigUtil.readSysValue("openoffice_home");
		// 启动OpenOffice的服务
		// String command = OpenOffice_HOME +
		// ConfigUtil.readSysValue("openoffice_command");;

		String[] commands = new String[] {
				OpenOffice_HOME + "/program/soffice",
				"-headless",
				"-accept=socket,host=" + openofficeIp + ",port="
						+ openofficePort + ";urp;" };

		Process pro = Runtime.getRuntime().exec(commands);
		
		Thread.sleep(100);
		
		try {
			int i=0;
			for(; i<3; i++){
				try {
					connection = new SocketOpenOfficeConnection(openofficeIp,
							Integer.parseInt(openofficePort));
					// 连接openoffice server
					connection.connect();
					break;
				} catch (ConnectException ce) {
					Thread.sleep(1000);
				}				
			}
			if(i>=3){
				throw new Exception("connect open office fail!");
			}


			// 构造转换对象类
			DocumentConverter converter = new OpenOfficeDocumentConverter(
					connection);
			// 构造输出流
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			converter.convert(is, df, baos,
					formatReg.getFormatByFileExtension(FILE_TYPE_PDF));
			// 将流转成byte数组
			byte[] buf = new byte[1024];
			int len;
			while ((len = is.read(buf)) > 0) {
				baos.write(buf, 0, len);
			}

			byte[] dat = baos.toByteArray();

			if (!EmptyUtil.isEmpty(connection)) {
				connection.disconnect();
			}
			if (!EmptyUtil.isEmpty(is)) {
				is.close();
			}
			if (!EmptyUtil.isEmpty(baos)) {
				baos.close();
			}
			if (!EmptyUtil.isEmpty(mth_is)) {
				mth_is.close();
			}

			return dat;
		} finally {
			if (pro != null) {
				try {
					pro.destroy();
				} catch (Exception e) {

				}
			}
		}
	}

}
