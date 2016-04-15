package crawler.example;

import java.io.IOException;
import java.util.ArrayList;

import com.github.abola.crawler.CrawlerPack;

/**
 * 簡易練習
 * 
 * 找出所有文章中按推的id
 * 
 * @author Abola Lee
 *
 */
public class PttBaseballUrlsCrawler {
	
	public ArrayList<String> getPttUrls() throws InterruptedException {
		ArrayList<String> pttUrls = new ArrayList();
		for (int i = 2763; i > 0; i--) {
			String uri = "https://www.ptt.cc/bbs/Elephants/index" + i + ".html";
			String pttUrl = CrawlerPack.start().getFromHtml(uri).select("a:contains([轉播])").attr("href").toString();
			Thread.sleep(500);
			if(pttUrl.length()>0){
				pttUrls.add(pttUrl);
//				System.out.println(pttUrl + " added to Urls");
			}
		}
		return pttUrls;

	}

}
