package crawler.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;

public class PttBaseballContentCrawler {
	
	public String pttContentPostTime(String url) {
		Document pttContent = CrawlerPack.start().getFromHtml(url);
		return pttContent.select(".article-meta-tag:contains(時間) + .article-meta-value").text();
	}
	public String pttContentAuthor(String url) {
		Document pttContent = CrawlerPack.start().getFromHtml(url);
		return pttContent.select(".article-meta-tag:contains(作者) + .article-meta-value").text();
	}
	public String pttContentTitle(String url) {
		Document pttContent = CrawlerPack.start().getFromHtml(url);
		return pttContent.select(".article-meta-tag:contains(標題) + .article-meta-value").text();
	}
	public String pttContentPush(String url){
		Document pttContent = CrawlerPack.start().getFromHtml(url);
		return pttContent.select(".push-tag:contains(推) ~ span").text();
	}
	public String pttContentBoo(String url){
		Document pttContent = CrawlerPack.start().getFromHtml(url);
		return pttContent.select(".push-tag:contains(噓) ~ span").text();
	}
	public String pttContentMain(String url) {
		Document pttContent = CrawlerPack.start().getFromHtml(url);
		Elements pttContentMain = CrawlerPack.start().getFromHtml(url).select("#main-content");

		for (Element elem : pttContentMain
				.select(".article-metaline,.article-metaline-right, .push, span:contains(發信站), span:contains(文章網址)")) {
			elem.remove();
		}
		String pttContentMainInTxt = pttContentMain.text();
		return pttContentMainInTxt;
	}

}
