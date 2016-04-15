package crawler.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PttContentPutIntoSqlServer {

	static String url = "";
	static String team = "Lamigo";

	public static void main(String[] args) throws SQLException, InterruptedException, IOException {

		PttBaseballUrlsCrawler pbuc = new PttBaseballUrlsCrawler();
		PttBaseballContentCrawler pbcc = new PttBaseballContentCrawler();

		ArrayList<String> urls = pbuc.getPttUrls();
		System.out.println(urls);

		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1433;database=PttBaseballPosts;integratedSecurity=true");
		Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery("select * from " + team);
		rs.moveToInsertRow();

		for (int i = 0; i < urls.size(); i++) {
			if (i % 3 == 0) {
				Thread.sleep(1000);
			}

			// try{
			url = "https://www.ptt.cc" + urls.get(i);
			String pttTime, pttTitle, pttAuthor, pttPush, pttBoo, pttMain;
			pttTime = pbcc.pttContentPostTime(url);
			pttTitle = pbcc.pttContentTitle(url);
			pttAuthor = pbcc.pttContentAuthor(url);
			pttPush = pbcc.pttContentPush(url);
			pttBoo = pbcc.pttContentBoo(url);
			pttMain = pbcc.pttContentMain(url);

			rs.updateString("時間", pttTime);
			rs.updateString("標題", pttTitle);
			rs.updateString("作者", pttAuthor);
			rs.updateString("推", pttPush);
			rs.updateString("噓", pttBoo);
			rs.updateString("內文", pttMain);
			rs.insertRow();
			System.out.print(i + ",");

			Thread.sleep(1000);

			// }catch(Exception e){
			//
			// File file = new File("D://ErrorLog.txt");
			// BufferedWriter fw = null;
			// fw = new BufferedWriter(new OutputStreamWriter(new
			// FileOutputStream(file, true), "UTF-8")); // 指點編碼格式，以免讀取時中文字符異常
			// fw.write("test");
			// fw.newLine();
			// fw.write("test2");
			//
			// }

		}

		rs.close();
		statement.close();
		conn.close();
		System.out.println("Job done.");
	}

}
