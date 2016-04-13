package pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.github.abola.crawler.CrawlerPack;

public class CropCrawler2 {
	static String url = "http://m.coa.gov.tw/OpenData/FarmTransData.aspx?$top=1000&$skip=0";

	static String[] tableNames = { "FA1", "FA2", "FA4", "FA9", "FB0", "FB1", "FB2", "FB9", "FC1", "FC2", "FD0",
			"FD1", "FE0", "FE1", "FE2", "FE3", "FE9", "FF0", "FF1", "FF2", "FF3", "FF4", "FF9", "FG0", "FG1", "FG2",
			"FG3", "FG4", "FG9", "FH0", "FH1", "FH2", "FH3", "FH4", "FI0", "FI1", "FI2", "FI3", "FJ0", "FJ1", "FJ2",
			"FJ3", "FK0", "FK3", "FK4", "FK5", "FK9", "FL1", "FL2", "FL3", "FL4", "FL5", "FL6", "FL9", "FM0", "FM1",
			"FM2", "FM3", "FM9", "FN0", "FN1", "FN2", "FN3", "FN4", "FN5", "FN9", "FO1", "FO2", "FP1", "FP2", "FP3",
			"FQ1", "FQ2", "FQ9", "FR1", "FR2", "FR9", "FS0", "FS1", "FS2", "FT0", "FT1", "FT2", "FT3", "FT4", "FT5",
			"FT6", "FT7", "FT9", "FU1", "FU2", "FU3", "FV0", "FV1", "FV2", "FV3", "FV4", "FV5", "FV6", "FV9", "FW1",
			"FW2", "FW9", "FX1", "FX2", "FY0", "FY1", "FY2", "FY3", "FY4", "FY5", "FY6", "FY7", "FY9", "FZ1", "FZ2",
			"LA0", "LA1", "LA2", "LA3", "LA4", "LA5", "LA6", "LA9", "LB0", "LB1", "LB2", "LB8", "LC0", "LC1", "LC2",
			"LC3", "LC5", "LC6", "LC7", "LC9", "LD1", "LD2", "LD8", "LE1", "LE2", "LF0", "LF1", "LF2", "LF3", "LF8",
			"LG1", "LG2", "LG3", "LG4", "LG5", "LG8", "LG9", "LH1", "LH2", "LH9", "LI0", "LI1", "LI2", "LI3", "LI4",
			"LI5", "LI6", "LI8", "LI9", "LJ1", "LJ3", "LJ4", "LJ5", "LJ8", "LK0", "LK2", "LK3", "LK8", "LK9", "LL0",
			"LL1", "LL8", "LM1", "LM2", "LM8", "LN1", "LO1", "LP1", "LP2", "LP9", "LQ1", "LQ2", "LR1", "LS1", "LT1",
			"LT2", "LT3", "LU1", "LV1", "LV9", "LX1", "LX2", "LY1", "LY2", "LY3", "LY4", "LY5", "LY6", "LZ1", "LZ2",
			"MA0", "MA1", "MA2", "MB0", "MB1", "MC0", "MC1", "MC2", "MD0", "MD1", "ME0", "ME1", "ME2", "MF0", "MF1",
			"MF2", "MG0", "MG1", "MG2", "MH1", "MH2", "MI0", "MI1", "MI2", "MJ0", "MJ1", "MJ2", "MK0", "MK1", "MK2",
			"ML0", "ML1", "ML2", "MM0", "MM1", "MM2", "MN0", "MN1", "MN2", "MX1", "OA1", "OA2", "OA9", "OB1", "OC1",
			"OD1", "OE1", "OE2", "OG1", "OG2", "OH1", "OH2", "OH3", "OH4", "OI1", "OI2", "OI3", "OI4", "OL1",
			"SA0", "SA1", "SA2", "SA3", "SA4", "SA5", "SA6", "SA9", "SB1", "SB2", "SB9", "SC1", "SC9", "SD1", "SD9",
			"SE1", "SE2", "SE3", "SE4", "SE5", "SE6", "SE9", "SF1", "SF2", "SF3", "SF4", "SG1", "SG2", "SG3", "SG4",
			"SG5", "SG6", "SG7", "SG9", "SH1", "SH2", "SH3", "SH4", "SH5", "SH6", "SH7", "SH9", "SI1", "SJ1", "SJ2",
			"SJ3", "SJ4", "SJ9", "SK1", "SK2", "SL1", "SL2", "SM1", "SM9", "SN1", "SN2", "SN9", "SO0", "SO1", "SO2",
			"SO3", "SP0", "SP1", "SP2", "SP3", "SP9", "SQ1", "SQ3", "SQ9", "SR1", "SR3", "SR4", "SS0", "SS1", "ST1",
			"SU0", "SU1", "SU2", "SU3", "SU4", "SU9", "SV1", "SV2", "SV3", "SV9", "SW1", "SX0", "SX1", "SX2", "SX3",
			"SX4", "SX5", "SY1", "SZ1", "SZ2", "SZ3", "SZ5", "SZ6", "SZ7" };

//	static String[] vegetableNames = {"蘿蔔-梅花","蘿蔔-椿仔","蘿蔔-矸仔","蘿蔔-櫻桃","蘿蔔-青色","蘿蔔-白玉","蘿蔔-其他","蘿蔔-進口","胡蘿蔔-未洗","胡蘿蔔-清洗","胡蘿蔔-進口","馬鈴薯-本產","馬鈴薯-進口","洋蔥-本產","洋蔥-進口","青蔥-日蔥","青蔥-北蔥","青蔥-大蔥","青蔥-珠蔥","青蔥-紅蔥頭","青蔥-粉蔥","青蔥-進口","韭菜-白頭","韭菜-韭菜黃","韭菜-韭菜花","韭菜-青頭","大蒜-硬梗","大蒜-軟梗","大蒜-蒜苔","大蒜-蔥蒜","大蒜-蒜頭","大蒜-蒜仁","大蒜-蒜瓣","大蒜-進口","竹筍-麻竹筍","竹筍-綠竹筍","竹筍-桂竹筍","竹筍-孟宗筍","竹筍-烏殼綠","竹筍-箭竹筍","竹筍-去殼","竹筍-進口","萵苣莖","芋-麵芋","芋-檳榔心芋","芋-里芋","芋-芋莖","芋-進口","荸薺","荸薺-加工","豆薯","菊芋-雪蓮薯","牛蒡","牛蒡-進口","蓮藕","蓮藕-蓮子","蓮藕-進口","甘藷-臺農57號","甘藷-芋仔甘藷","甘藷-臺農66號","甘藷-其他","薑-老薑","薑-嫩薑","薑-粉薑","薑-其他","薑-進口","茭白筍-帶殼","茭白筍-去殼","茭白筍-進口","菱角-生菱角","菱角-去殼","菱角-熟菱角","大心菜-帶葉","大心菜-其他","蕎頭-露蕎","薯蕷-紅薯蕷","薯蕷-白薯蕷","薯蕷-樹薯","薯蕷-粉薯","薯蕷-其他","薯蕷-進口","蘆筍-白蘆筍","蘆筍-綠蘆筍","蘆筍-蘆筍花","蘆筍-進口","球莖甘藍","芽菜類-綠豆芽","芽菜類-黃豆芽","芽菜類-豌豆嬰","芽菜類-苜蓿芽","芽菜類-蘿蔔嬰","芽菜類-其他","慈菇","半天筍","甘蔗筍","金針筍","百合","草石蠶-冬蟲夏草","半天花","晚香玉筍","甘藍-初秋","甘藍-改良種","甘藍-甜甘藍","甘藍-紫色","甘藍-甘藍心","甘藍-甘藍芽","甘藍-其他","甘藍-進口","小白菜-土白菜","小白菜-蚵仔白","小白菜-水耕","小白菜-其他","包心白菜-包白","包心白菜-成功白","包心白菜-包頭蓮","包心白菜-天津白","包心白菜-冬白芽","包心白菜-大土","包心白菜-其他","包心白菜-進口","青江白菜-小梗","青江白菜-大梗","青江白菜-水耕","皇宮菜-小葉","皇宮菜-大葉","蕹菜-大葉","蕹菜-小葉","蕹菜-水蕹菜","蕹菜-水耕","蕹菜-其他","芹菜-青梗","芹菜-白梗","芹菜-西洋芹菜","芹菜-山芹菜","芹菜-芹菜管","芹菜-水耕","芹菜-進口","菠菜-圓葉","菠菜-角葉","菠菜-進口","萵苣菜-廣東萵","萵苣菜-結球萵","萵苣菜-本島圓葉","萵苣菜-本島尖葉","萵苣菜-油麥菜","萵苣菜-蘿美","萵苣菜-水耕","萵苣菜-其他","萵苣菜-進口","芥菜-芥菜仁","芥菜-大芥菜","芥菜-小芥菜","芥菜-翡翠娃娃菜","芥菜-水耕","芥藍菜","芥藍菜-芥藍芽","芥藍菜-水耕","芥藍菜-其他","芥藍菜-進口","茼蒿","茼蒿-水耕","茼蒿-其他","莧菜-紅莧菜","莧菜-白莧菜","莧菜-水耕","油菜","甘藷葉","芫荽","芫荽-進口","九層塔","紅鳳菜-紅鳳菜","紅鳳菜-白鳳菜","塌棵菜","茴香","海菜-龍鬚菜","海菜-海帶","海菜-水蓮","菾菜-茄茉菜","巴西利","巴西利-進口","蕨菜-過貓","蕨菜-山蘇","西洋菜","黑甜仔菜","豬母菜","人參葉","珍珠菜","香椿","薺菜","藤川七-川七葉","黃秋葵","樊花","瓊花","其他花類","百果-進口","花椰菜-青梗","花椰菜-白梗","花椰菜-其他","花椰菜-進口","胡瓜-黑刺","胡瓜-改良種","花胡瓜","花胡瓜-其他","花胡瓜-進口","冬瓜-白皮","冬瓜-青皮","冬瓜-毛瓜","冬瓜-其他","冬瓜-進口","絲瓜","絲瓜-角瓜","絲瓜-白瓜","絲瓜-長絲瓜","絲瓜-其他","絲瓜-進口","苦瓜-白大米","苦瓜-青大米","苦瓜-山苦瓜","苦瓜-翠綠","苦瓜-其他","苦瓜-進口","扁蒲-長形","扁蒲-圓形","扁蒲-花蒲","扁蒲-梨子蒲","扁蒲-其他","茄子-胭脂茄","茄子-麻荸茄","茄子-日本種","茄子-其他","蕃茄-黑柿","蕃茄-粉柿","蕃茄-牛蕃茄","蕃茄-其他","甜椒-新香","甜椒-彩色種","甜椒-青椒","甜椒-其他","甜椒-進口","豌豆-白花","豌豆-紅花","豌豆-豆莢","豌豆-豆仁","豌豆-豌豆苗","豌豆-甜豌豆","豌豆-進口","菜豆-白色","菜豆-青色","菜豆-紅色","菜豆-其他","菜豆-進口","敏豆-白豆","敏豆-青豆","敏豆-粉豆","敏豆-翼豆","敏豆-醜豆","敏豆-其他","敏豆-進口","蠶豆-豆莢","蠶豆-豆仁","萊豆-豆莢","萊豆-豆仁","鵲豆-肉豆","毛豆-豆莢","毛豆-豆仁","毛豆-進口","青花苔","青花苔-青花筍","青花苔-進口","越瓜-青色","越瓜-白色","越瓜-其他","南瓜-木瓜型","南瓜-圓型","南瓜-黃如意","南瓜-觀賞","南瓜-青如意","南瓜-東昇","南瓜-栗子","南瓜-其他","南瓜-進口","隼人瓜","隼人瓜-瓜苗","石蓮花","辣椒-紅小","辣椒-青小","辣椒-雞心","辣椒-朝天椒","辣椒-青龍","辣椒-糯米椒","辣椒-其他","辣椒-進口","金針花","金針花-進口","洛神花","花豆-花豆","虎豆-福豆","玉米-白玉米","玉米-甜硬殼","玉米-超甜白","玉米-玉米筍","玉米-糯米種","玉米-甜軟殼","玉米-糯米白","玉米-其他","玉米-進口","落花生-生","落花生-熟","洋菇","洋菇-盒裝","洋菇-其他","草菇","草菇-其他","濕木耳","濕木耳-盒裝","濕木耳-其他","濕香菇","濕香菇-其他","金絲菇","金絲菇-盒裝","金絲菇-其他","蠔菇-鮑魚菇","蠔菇-盒裝","蠔菇-其他","巴西蘑菇","巴西蘑菇-盒裝","巴西蘑菇-其他","松茸","松茸-盒裝","秀珍菇","秀珍菇-盒裝","秀珍菇-其他","杏鮑菇","杏鮑菇-盒裝","杏鮑菇-其他","鴻禧菇","鴻禧菇-盒裝","鴻禧菇-其他","珊瑚菇","珊瑚菇-盒裝","珊瑚菇-其他","猴頭菇","猴頭菇-盒裝","猴頭菇-其他","柳松菇","柳松菇-盒裝","柳松菇-其他","其他菇類-其他菇類","鹹菜-清心","鹹菜-帶葉","鹹菜-進口","雪裡紅","榨菜","蘿蔔乾","醃瓜-乾","醃瓜-漬","熟筍-桂竹筍","熟筍-箭竹筍","桶筍-麻竹筍","桶筍-綠竹筍","桶筍-桂竹筍","桶筍-箭竹筍","筍乾","筍絲","筍片","筍茸","朴菜","其他"};
//	
//	static String[] tableNames = {"SA1","SA2","SA3","SA4","SA5","SA6","SA0","SA9","SB1","SB2","SB9","SC1","SC9","SD1","SD9","SE1","SE2","SE3","SE4","SE5","SE6","SE9","SF1","SF2","SF3","SF4","SG1","SG2","SG3","SG4","SG5","SG6","SG7","SG9","SH1","SH2","SH3","SH4","SH5","SH6","SH7","SH9","SI1","SJ1","SJ2","SJ3","SJ4","SJ9","SK1","SK2","SL1","SL2","SM1","SM9","SN1","SN2","SN9","SO1","SO2","SO3","SO0","SP1","SP2","SP3","SP0","SP9","SQ1","SQ3","SQ9","SR1","SR3","SR4","SS1","SS0","ST1","SU1","SU2","SU3","SU4","SU0","SU9","SV1","SV2","SV3","SV9","SW1","SX1","SX2","SX3","SX4","SX5","SX0","SY1","SZ1","SZ2","SZ3","SZ4","SZ5","SZ6","SZ7","LA1","LA2","LA3","LA4","LA5","LA6","LA0","LA9","LB1","LB2","LB8","LB0","LC1","LC2","LC3","LC5","LC6","LC7","LC0","LC9","LD1","LD2","LD8","LE1","LE2","LF1","LF2","LF3","LF8","LF0","LG1","LG2","LG3","LG4","LG5","LG8","LG9","LH1","LH2","LH9","LI1","LI2","LI3","LI4","LI5","LI6","LI8","LI0","LI9","LJ1","LJ3","LJ4","LJ5","LJ8","LK2","LK3","LK8","LK0","LK9","LL1","LL8","LL0","LM1","LM2","LM8","LN1","LO1","LP1","LP9","LP2","LQ1","LQ2","LR1","LS1","LT1","LT2","LT3","LU1","LV1","LV9","LX1","LX2","LY1","LY2","LY3","LY4","LY5","LY6","LZ1","LZ2","FA1","FA2","FA4","FA0","FA9","FB1","FB2","FB0","FB9","FC1","FC2","FD1","FD0","FD9","FE1","FE2","FE3","FE0","FE9","FF1","FF2","FF3","FF4","FF0","FF9","FG1","FG2","FG3","FG4","FG0","FG9","FH1","FH2","FH3","FH4","FH0","FI1","FI2","FI3","FI0","FJ1","FJ2","FJ3","FJ0","FK3","FK4","FK5","FK0","FK9","FL1","FL2","FL3","FL4","FL5","FL6","FL9","FM1","FM2","FM3","FM0","FM9","FN1","FN2","FN3","FN4","FN5","FN0","FN9","FO1","FO2","FP1","FP2","FP3","FQ1","FQ2","FQ9","FR1","FR2","FR9","FS1","FS2","FS0","FT1","FT2","FT3","FT4","FT5","FT6","FT7","FT0","FT9","FU1","FU2","FU3","FV1","FV2","FV3","FV4","FV5","FV6","FV0","FV9","FW1","FW9","FW2","FX1","FX2","FY1","FY2","FY3","FY4","FY5","FY6","FY7","FY0","FY9","FZ1","FZ2","MA1","MA2","MA0","MB1","MB0","MC1","MC2","MC0","MD1","MD0","ME1","ME2","ME0","MF1","MF2","MF0","MG1","MG2","MG0","MH1","MH2","MI1","MI2","MI0","MJ1","MJ2","MJ0","MK1","MK2","MK0","ML1","ML2","ML0","MM1","MM2","MM0","MN1","MN2","MN0","MX1","OA1","OA2","OA9","OB1","OC1","OD1","OE1","OE2","OG1","OG2","OH1","OH2","OH3","OH4","OI1","OI2","OI3","OI4","OL1","OX1"};

	// String tableToBeAltered = tableNames[i];
	// String sqlUrl = "delete from " + tableNames[i] + " where 市場代號 =
	// 105 or 市場代號 = 700";
	// String sqlUrl2 = "ALTER TABLE " + tableNames[i] + " ADD 作物代號
	// varchar(5)";
	// String sqlUrl3 = "UPDATE " + tableNames[i] + " SET 作物代號 = '" +
	// tableNames[i] + "' WHERE 作物名稱 != '休市'";
//	String sqlFindMoreThanMaxRoll = "select count(交易日期) as 'count' from " + tableNames[i] + "";
	// statement.executeUpdate(sqlUrl3);
	
	public static void main(String[] args) throws SQLException {
		Document original = CrawlerPack.start().getFromJson(url);

		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1433;database=Vegetables;integratedSecurity=true");
		Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		for (int i = 0; i < tableNames.length; i++) {
//			 String tableToBeAltered = tableNames[i];
//			 String sqlUrl = "delete from " + tableNames[i] + " where 作物代號  != '" + tableNames[i] +"' and 作物名稱 != '休市'";
			// String sqlUrl2 = "ALTER TABLE " + tableNames[i] + " ADD 作物代號
			// varchar(5)";
			// String sqlUrl3 = "UPDATE " + tableNames[i] + " SET 作物代號 = '" +
			// tableNames[i] + "' WHERE 作物名稱 != '休市'";
			String sqlFindMoreThanMaxRoll = "select count(交易日期) as 'count' from " + tableNames[i];
//			 statement.executeUpdate(sqlUrl);
			ResultSet rs = statement.executeQuery(sqlFindMoreThanMaxRoll);
			while (rs.next()) {
				if (rs.getInt("count") > 9000) {
					System.out.println(tableNames[i] + ":" + rs.getInt(1));
				}

			}

			// rs.moveToInsertRow();
			//
			// for (Element elem : original.select("row")) {
			//
			// rs.updateString("交易日期", elem.select("交易日期").text());
			// rs.updateString("作物名稱", elem.select("作物名稱").text());
			// rs.updateString("平均價", elem.select("平均價").text());
			// rs.insertRow();
			// }
			// rs.close();

		}
		statement.close();
		conn.close();
		System.out.println("job done.");
	}
}
