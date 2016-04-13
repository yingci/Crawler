package pkg;

import com.github.abola.crawler.CrawlerPack;

public class BaseballCrawlerTest {

	public static void main(String[] args) {
		String url = "https://www.ptt.cc/bbs/StupidClown/index.html";

		CrawlerPack.start()
		    .getFromHtml(url)
		    .select("div.title > a").text();

	}

}
