package crawler.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class WritePttUrlsIntoTxt {

	public static void baseBallPTT(String ptt) throws IOException {

			BufferedWriter fw = null;

			try {

				File file = new File("D://PttbaseballUrls.txt");

				fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指點編碼格式，以免讀取時中文字符異常
				String[] ptts = new String[]{};
				ptts= ptt.split("</a>");
				for(String content:ptts){
					if(content.length()!=0){
					fw.write(content);
					fw.newLine();
					}
				}
			//fw.write(ptt + ",");
			//	fw.newLine();
				fw.flush(); // 全部寫入緩存中的內容

			} catch (Exception e) {

				e.printStackTrace();

			} finally{
				fw.close();
			}
	}

	

}
