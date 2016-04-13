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

//	static String[] vegetableNames = {"�ڽ�-����","�ڽ�-�ϥJ","�ڽ�-�z�J","�ڽ�-���","�ڽ�-�C��","�ڽ�-�ե�","�ڽ�-��L","�ڽ�-�i�f","�J�ڽ�-���~","�J�ڽ�-�M�~","�J�ڽ�-�i�f","���a��-����","���a��-�i�f","�v��-����","�v��-�i�f","�C��-�齵","�C��-�_��","�C��-�j��","�C��-�]��","�C��-�����Y","�C��-����","�C��-�i�f","����-���Y","����-�����","����-�����","����-�C�Y","�j�[-�w��","�j�[-�n��","�j�[-�[�a","�j�[-���[","�j�[-�[�Y","�j�[-�[��","�j�[-�[ä","�j�[-�i�f","�˵�-�¦˵�","�˵�-��˵�","�˵�-�ۦ˵�","�˵�-�s�v��","�˵�-�Q�ߺ�","�˵�-�b�˵�","�˵�-�h��","�˵�-�i�f","���U��","��-�Ѩ�","��-�b�}�ߨ�","��-����","��-����","��-�i�f","����","����-�[�u","����","�⨡-������","���","���-�i�f","����","����-���l","����-�i�f","����-�O�A57��","����-���J����","����-�O�A66��","����-��L","��-����","��-����","��-����","��-��L","��-�i�f","�t�յ�-�a��","�t�յ�-�h��","�t�յ�-�i�f","�٨�-�͵٨�","�٨�-�h��","�٨�-���٨�","�j�ߵ�-�a��","�j�ߵ�-��L","�L�Y-�S�L","���@-�����@","���@-�����@","���@-����","���@-����","���@-��L","���@-�i�f","Ī��-��Ī��","Ī��-��Ī��","Ī��-Ī����","Ī��-�i�f","�y������","�޵���-�񨧪�","�޵���-������","�޵���-�ܨ���","�޵���-�`����","�޵���-�ڽ���","�޵���-��L","�Oۣ","�b�ѵ�","�̽���","���w��","�ʦX","�����-�V�ήL��","�b�Ѫ�","�߭��ɵ�","����-���","����-��}��","����-������","����-����","����-���Ť�","����-���Ū�","����-��L","����-�i�f","�p�յ�-�g�յ�","�p�յ�-�H�J��","�p�յ�-����","�p�յ�-��L","�]�ߥյ�-�]��","�]�ߥյ�-���\��","�]�ߥյ�-�]�Y��","�]�ߥյ�-�Ѭz��","�]�ߥյ�-�V�ժ�","�]�ߥյ�-�j�g","�]�ߥյ�-��L","�]�ߥյ�-�i�f","�C���յ�-�p��","�C���յ�-�j��","�C���յ�-����","�Ӯc��-�p��","�Ӯc��-�j��","�R��-�j��","�R��-�p��","�R��-���R��","�R��-����","�R��-��L","���-�C��","���-�ձ�","���-��v���","���-�s���","���-����","���-����","���-�i�f","�Ե�-�긭","�Ե�-����","�Ե�-�i�f","���U��-�s�F��","���U��-���y��","���U��-���q�긭","���U��-���q�y��","���U��-�o����","���U��-�ڬ�","���U��-����","���U��-��L","���U��-�i�f","���-��椯","���-�j���","���-�p���","���-�B�A������","���-����","���ŵ�","���ŵ�-���Ū�","���ŵ�-����","���ŵ�-��L","���ŵ�-�i�f","Ӧ�U","Ӧ�U-����","Ӧ�U-��L","�A��-���A��","�A��-�ճA��","�A��-����","�o��","���Ÿ�","ʹ�h","ʹ�h-�i�f","�E�h��","�����-�����","�����-�ջ��","��ʵ�","����","����-�sŽ��","����-���a","����-����","۰��-�X�[��","�ڦ�Q","�ڦ�Q-�i�f","����-�L��","����-�sĬ","��v��","�²��J��","�ޥ���","�H�Ѹ�","�ï]��","����","�ɵ�","�ät�C-�t�C��","���","�Ԫ�","ã��","��L����","�ʪG-�i�f","�ᷦ��-�C��","�ᷦ��-�ձ�","�ᷦ��-��L","�ᷦ��-�i�f","�J��-�¨�","�J��-��}��","��J��","��J��-��L","��J��-�i�f","�V��-�ե�","�V��-�C��","�V��-���","�V��-��L","�V��-�i�f","����","����-����","����-�ե�","����-������","����-��L","����-�i�f","�W��-�դj��","�W��-�C�j��","�W��-�s�W��","�W��-�A��","�W��-��L","�W��-�i�f","��Z-����","��Z-���","��Z-��Z","��Z-���l�Z","��Z-��L","�X�l-�گ׭X","�X�l-�²�X","�X�l-�饻��","�X�l-��L","���X-�¬U","���X-���U","���X-�����X","���X-��L","����-�s��","����-�m���","����-�C��","����-��L","����-�i�f","�ܨ�-�ժ�","�ܨ�-����","�ܨ�-����","�ܨ�-����","�ܨ�-�ܨ��]","�ܨ�-���ܨ�","�ܨ�-�i�f","�樧-�զ�","�樧-�C��","�樧-����","�樧-��L","�樧-�i�f","�Ө�-�ը�","�Ө�-�C��","�Ө�-����","�Ө�-�l��","�Ө�-�ਧ","�Ө�-��L","�Ө�-�i�f","����-����","����-����","�ܨ�-����","�ܨ�-����","�N��-�ר�","��-����","��-����","��-�i�f","�C��a","�C��a-�C�ᵫ","�C��a-�i�f","�V��-�C��","�V��-�զ�","�V��-��L","�n��-��ʫ�","�n��-�ꫬ","�n��-���p�N","�n��-�[��","�n��-�C�p�N","�n��-�F�@","�n��-�ߤl","�n��-��L","�n��-�i�f","�G�H��","�G�H��-�ʭ]","�۽���","����-���p","����-�C�p","����-����","����-�¤Ѵ�","����-�C�s","����-�z�̴�","����-��L","����-�i�f","���w��","���w��-�i�f","������","�ᨧ-�ᨧ","�ꨧ-�֨�","�ɦ�-�եɦ�","�ɦ�-���w��","�ɦ�-�W����","�ɦ�-�ɦ̵�","�ɦ�-�z�̺�","�ɦ�-���n��","�ɦ�-�z�̥�","�ɦ�-��L","�ɦ�-�i�f","�����-��","�����-��","�vۣ","�vۣ-����","�vۣ-��L","��ۣ","��ۣ-��L","����","����-����","����-��L","�㭻ۣ","�㭻ۣ-��L","����ۣ","����ۣ-����","����ۣ-��L","Įۣ-�j��ۣ","Įۣ-����","Įۣ-��L","�ڦ�Ĩۣ","�ڦ�Ĩۣ-����","�ڦ�Ĩۣ-��L","�Q��","�Q��-����","�q��ۣ","�q��ۣ-����","�q��ۣ-��L","���jۣ","���jۣ-����","���jۣ-��L","�E�Hۣ","�E�Hۣ-����","�E�Hۣ-��L","����ۣ","����ۣ-����","����ۣ-��L","�U�Yۣ","�U�Yۣ-����","�U�Yۣ-��L","�h�Qۣ","�h�Qۣ-����","�h�Qۣ-��L","��Lۣ��-��Lۣ��","�е�-�M��","�е�-�a��","�е�-�i�f","���̬�","�^��","�ڽ���","�M��-��","�M��-�{","����-�ۦ˵�","����-�b�˵�","��-�¦˵�","��-��˵�","��-�ۦ˵�","��-�b�˵�","����","����","����","����","����","��L"};
//	
//	static String[] tableNames = {"SA1","SA2","SA3","SA4","SA5","SA6","SA0","SA9","SB1","SB2","SB9","SC1","SC9","SD1","SD9","SE1","SE2","SE3","SE4","SE5","SE6","SE9","SF1","SF2","SF3","SF4","SG1","SG2","SG3","SG4","SG5","SG6","SG7","SG9","SH1","SH2","SH3","SH4","SH5","SH6","SH7","SH9","SI1","SJ1","SJ2","SJ3","SJ4","SJ9","SK1","SK2","SL1","SL2","SM1","SM9","SN1","SN2","SN9","SO1","SO2","SO3","SO0","SP1","SP2","SP3","SP0","SP9","SQ1","SQ3","SQ9","SR1","SR3","SR4","SS1","SS0","ST1","SU1","SU2","SU3","SU4","SU0","SU9","SV1","SV2","SV3","SV9","SW1","SX1","SX2","SX3","SX4","SX5","SX0","SY1","SZ1","SZ2","SZ3","SZ4","SZ5","SZ6","SZ7","LA1","LA2","LA3","LA4","LA5","LA6","LA0","LA9","LB1","LB2","LB8","LB0","LC1","LC2","LC3","LC5","LC6","LC7","LC0","LC9","LD1","LD2","LD8","LE1","LE2","LF1","LF2","LF3","LF8","LF0","LG1","LG2","LG3","LG4","LG5","LG8","LG9","LH1","LH2","LH9","LI1","LI2","LI3","LI4","LI5","LI6","LI8","LI0","LI9","LJ1","LJ3","LJ4","LJ5","LJ8","LK2","LK3","LK8","LK0","LK9","LL1","LL8","LL0","LM1","LM2","LM8","LN1","LO1","LP1","LP9","LP2","LQ1","LQ2","LR1","LS1","LT1","LT2","LT3","LU1","LV1","LV9","LX1","LX2","LY1","LY2","LY3","LY4","LY5","LY6","LZ1","LZ2","FA1","FA2","FA4","FA0","FA9","FB1","FB2","FB0","FB9","FC1","FC2","FD1","FD0","FD9","FE1","FE2","FE3","FE0","FE9","FF1","FF2","FF3","FF4","FF0","FF9","FG1","FG2","FG3","FG4","FG0","FG9","FH1","FH2","FH3","FH4","FH0","FI1","FI2","FI3","FI0","FJ1","FJ2","FJ3","FJ0","FK3","FK4","FK5","FK0","FK9","FL1","FL2","FL3","FL4","FL5","FL6","FL9","FM1","FM2","FM3","FM0","FM9","FN1","FN2","FN3","FN4","FN5","FN0","FN9","FO1","FO2","FP1","FP2","FP3","FQ1","FQ2","FQ9","FR1","FR2","FR9","FS1","FS2","FS0","FT1","FT2","FT3","FT4","FT5","FT6","FT7","FT0","FT9","FU1","FU2","FU3","FV1","FV2","FV3","FV4","FV5","FV6","FV0","FV9","FW1","FW9","FW2","FX1","FX2","FY1","FY2","FY3","FY4","FY5","FY6","FY7","FY0","FY9","FZ1","FZ2","MA1","MA2","MA0","MB1","MB0","MC1","MC2","MC0","MD1","MD0","ME1","ME2","ME0","MF1","MF2","MF0","MG1","MG2","MG0","MH1","MH2","MI1","MI2","MI0","MJ1","MJ2","MJ0","MK1","MK2","MK0","ML1","ML2","ML0","MM1","MM2","MM0","MN1","MN2","MN0","MX1","OA1","OA2","OA9","OB1","OC1","OD1","OE1","OE2","OG1","OG2","OH1","OH2","OH3","OH4","OI1","OI2","OI3","OI4","OL1","OX1"};

	// String tableToBeAltered = tableNames[i];
	// String sqlUrl = "delete from " + tableNames[i] + " where �����N�� =
	// 105 or �����N�� = 700";
	// String sqlUrl2 = "ALTER TABLE " + tableNames[i] + " ADD �@���N��
	// varchar(5)";
	// String sqlUrl3 = "UPDATE " + tableNames[i] + " SET �@���N�� = '" +
	// tableNames[i] + "' WHERE �@���W�� != '��'";
//	String sqlFindMoreThanMaxRoll = "select count(������) as 'count' from " + tableNames[i] + "";
	// statement.executeUpdate(sqlUrl3);
	
	public static void main(String[] args) throws SQLException {
		Document original = CrawlerPack.start().getFromJson(url);

		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1433;database=Vegetables;integratedSecurity=true");
		Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		for (int i = 0; i < tableNames.length; i++) {
//			 String tableToBeAltered = tableNames[i];
//			 String sqlUrl = "delete from " + tableNames[i] + " where �@���N��  != '" + tableNames[i] +"' and �@���W�� != '��'";
			// String sqlUrl2 = "ALTER TABLE " + tableNames[i] + " ADD �@���N��
			// varchar(5)";
			// String sqlUrl3 = "UPDATE " + tableNames[i] + " SET �@���N�� = '" +
			// tableNames[i] + "' WHERE �@���W�� != '��'";
			String sqlFindMoreThanMaxRoll = "select count(������) as 'count' from " + tableNames[i];
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
			// rs.updateString("������", elem.select("������").text());
			// rs.updateString("�@���W��", elem.select("�@���W��").text());
			// rs.updateString("������", elem.select("������").text());
			// rs.insertRow();
			// }
			// rs.close();

		}
		statement.close();
		conn.close();
		System.out.println("job done.");
	}
}
