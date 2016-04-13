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
	static String[] highVolumeVegsName = {"���","�ᷦ��-�C��","�J��-�¨�","��J��","�V��-�ե�","����","�W��-�դj��","��Z-��Z","��Z-���l�Z","�X�l-�گ׭X","�X�l-�²�X","���X-�¬U","���X-�����X","����-�m���","����-�C��","�ܨ�-���ܨ�","�樧-�C��","�Ө�-�ը�","�Ө�-�ਧ","�C��a","�G�H��","����-���p","����-�¤Ѵ�","�ɦ�-�ɦ̵�","�ɦ�-���n��","����-���","�p�յ�-�g�յ�","�p�յ�-�H�J��","�]�ߥյ�-�]��","�C���յ�-�p��","�R��-�p��","���-�ձ�","�Ե�-�긭","���U��-���y��","���U��-���q�긭","���U��-�o����","���-�j���","���-�p���","���ŵ�","Ӧ�U","�A��-�ճA��","�o��","���Ÿ�","ʹ�h","�E�h��","�����","�vۣ","����","����ۣ","�q��ۣ","���jۣ","�E�Hۣ","�ڽ�-�z�J","�J�ڽ�-�M�~","���a��-����","�v��-����","�C��-�_��","�C��-����","����-���Y","����-�����","�j�[-�n��","�j�[-�[�Y","��-�b�}�ߨ�","���","����","��-����","�t�յ�-�a��","���@-�����@","�y������"};
	
	public static void main(String[] args) throws SQLException {
		String url=null;
		

		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1433;database=HighVolumeVegetables;integratedSecurity=true");
		Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		for (int i = 0; i < highVolumeVegsName.length; i++) {
			url = "http://m.coa.gov.tw/OpenData/FarmTransData.aspx?$top=1000&$skip=0&Crop="+highVolumeVegsName[i]+"&StartDate=101.01.01&EndDate=102.12.31";
			Document original = CrawlerPack.start().getFromJson(url);
			String sql = "CREATE TABLE " + highVolumeVegsId[i] + 
					"(������ varchar(20), "
					+ "�@���N�� varchar(20), "
					+ "�@���W�� nvarchar(20), "
					+ "�����N�� varchar(20), "
					+ "�����W�� nvarchar(20), "
					+ "�W�� float, "
					+ "���� float, "
					+ "�U�� float, "
					+ "������ float, "
					+ "����q float";
			statement.executeUpdate(sql);
			
			ResultSet rs = statement.executeQuery("SELECT * FROM " + highVolumeVegsId[i]);

			 rs.moveToInsertRow();
			
			 for (Element elem : original.select("row")) {
			
			 rs.updateString("������", elem.select("������").text());
			 rs.updateString("�@���N��", elem.select("�@���N��").text());
			 rs.updateString("�@���W��", elem.select("�@���W��").text());
			 rs.updateString("�����N��", elem.select("�����N��").text());
			 rs.updateString("�����W��", elem.select("�����W��").text());
			 rs.updateString("�W��", elem.select("�W��").text());
			 rs.updateString("����", elem.select("����").text());
			 rs.updateString("�U��", elem.select("�U��").text());
			 rs.updateString("������", elem.select("������").text());
			 rs.updateString("����q", elem.select("����q").text());
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
