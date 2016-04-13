package pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.github.abola.crawler.CrawlerPack;

public class CropCrawler {
	
	static String[] highVolumeVegsId = {"FA1","FB1","FC1","FD1","FE1","FF1","FG1","FH3","FH4","FI1","FI2","FJ1","FJ3","FK4","FK5","FL6","FM2","FN1","FN5","FR1","FU1","FV1","FV4","FY4","FY6","LA1","LB1","LB2","LC1","LD1","LF2","LG2","LH1","LI2","LI3","LI5","LJ3","LJ4","LK2","LL1","LM2","LN1","LO1","LP1","LP2","LQ1","MA1","MC1","ME1","MI1","MJ1","MK1","SA3","SB2","SC1","SD1","SE2","SE6","SF1","SF3","SG2","SG5","SJ2","SM1","SN1","SP1","SQ1","SU2","SW1"};
	static String[] highVolumeVegsName = {"黃秋葵","花椰菜-青梗","胡瓜-黑刺","花胡瓜","冬瓜-白皮","絲瓜","苦瓜-白大米","扁蒲-花蒲","扁蒲-梨子蒲","茄子-胭脂茄","茄子-麻荸茄","蕃茄-黑柿","蕃茄-牛蕃茄","甜椒-彩色種","甜椒-青椒","豌豆-甜豌豆","菜豆-青色","敏豆-白豆","敏豆-醜豆","青花苔","隼人瓜","辣椒-紅小","辣椒-朝天椒","玉米-玉米筍","玉米-甜軟殼","甘藍-初秋","小白菜-土白菜","小白菜-蚵仔白","包心白菜-包白","青江白菜-小梗","蕹菜-小葉","芹菜-白梗","菠菜-圓葉","萵苣菜-結球萵","萵苣菜-本島圓葉","萵苣菜-油麥菜","芥菜-大芥菜","芥菜-小芥菜","芥藍菜","茼蒿","莧菜-白莧菜","油菜","甘藷葉","芫荽","九層塔","紅鳳菜","洋菇","濕木耳","金絲菇","秀珍菇","杏鮑菇","鴻禧菇","蘿蔔-矸仔","胡蘿蔔-清洗","馬鈴薯-本產","洋蔥-本產","青蔥-北蔥","青蔥-粉蔥","韭菜-白頭","韭菜-韭菜花","大蒜-軟梗","大蒜-蒜頭","芋-檳榔心芋","牛蒡","蓮藕","薑-老薑","茭白筍-帶殼","薯蕷-白薯蕷","球莖甘藍"};
	
	public static void main(String[] args) throws SQLException {
		String url=null;
		

		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1433;database=HighVolumeVegetables;integratedSecurity=true");
		Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		for (int i = 0; i < highVolumeVegsName.length; i++) {
			url = "http://m.coa.gov.tw/OpenData/FarmTransData.aspx?$top=1000&$skip=0&Crop="+highVolumeVegsName[i]+"&StartDate=101.01.01&EndDate=102.12.31";
			Document original = CrawlerPack.start().getFromJson(url);
			String sql = "CREATE TABLE " + highVolumeVegsId[i] + 
					"(交易日期 varchar(20), "
					+ "作物代號 varchar(20), "
					+ "作物名稱 nvarchar(20), "
					+ "市場代號 varchar(20), "
					+ "市場名稱 nvarchar(20), "
					+ "上價 float, "
					+ "中價 float, "
					+ "下價 float, "
					+ "平均價 float, "
					+ "交易量 float";
			statement.executeUpdate(sql);
			
			ResultSet rs = statement.executeQuery("SELECT * FROM " + highVolumeVegsId[i]);

			 rs.moveToInsertRow();
			
			 for (Element elem : original.select("row")) {
			
			 rs.updateString("交易日期", elem.select("交易日期").text());
			 rs.updateString("作物代號", elem.select("作物代號").text());
			 rs.updateString("作物名稱", elem.select("作物名稱").text());
			 rs.updateString("市場代號", elem.select("市場代號").text());
			 rs.updateString("市場名稱", elem.select("市場名稱").text());
			 rs.updateString("上價", elem.select("上價").text());
			 rs.updateString("中價", elem.select("中價").text());
			 rs.updateString("下價", elem.select("下價").text());
			 rs.updateString("平均價", elem.select("平均價").text());
			 rs.updateString("交易量", elem.select("交易量").text());
			 rs.insertRow();
			 }
			 rs.close();
			 System.out.println(highVolumeVegsName[i]+" table created, data inserted");
		}
		statement.close();
		conn.close();
		
		System.out.println("job done.");
	}
}
