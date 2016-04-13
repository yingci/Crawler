package pkg;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * �d��: �ϥΪ��Υ]���o�K�����̫�50�g�峹�����}
 */
class PttGossiping {

    final static String gossipMainPage = "https://www.ptt.cc/bbs/Gossiping/index.html";
    final static String gossipIndexPage = "https://www.ptt.cc/bbs/Gossiping/index%s.html";
    // ���o�̫�X�g���峹�ƶq
    static Integer loadLastPosts = 50;

    public static void main(String[] argv){

        String prevPage =
            CrawlerPack.start()
                .addCookie("over18","1")                // �K�����i�J�ݭn�]�wcookie
                .getFromHtml(gossipMainPage)            // ���ݸ�Ʈ榡�� HTML
                .select(".action-bar .pull-right > a")  // ���o�k�W���y�e�@���z�����e
                .get(1).attr("href")
                .replaceAll("/bbs/Gossiping/index([0-9]+).html", "$1");
        // �ثe�̥��� index �s��
        Integer lastPage = Integer.valueOf(prevPage)+1;

        List<String> lastPostsLink = new ArrayList<String>();

        while ( loadLastPosts > lastPostsLink.size() ){
            String currPage = String.format(gossipIndexPage, lastPage--);

            Elements links =
                CrawlerPack.start()
                    .addCookie("over18", "1")
                    .getFromHtml(currPage)
                    .select(".title > a");

            for( Element link: links) lastPostsLink.add( link.attr("href") );
        }

        // �˵����G
        for(String url : lastPostsLink){
            System.out.println(url);
        }
    }
}

public class BaseballCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
