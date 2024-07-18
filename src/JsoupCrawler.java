import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupCrawler {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://www.facebook.com").get();
        String title = doc.title();
        System.out.println("title: " + title);

        Elements links = doc.select("a[href]");

        for(Element link : links){
            System.out.println("\nlink: "+link.attr("href"));
            System.out.println("text: " + link.text());

        }

        System.out.println("Getting all Images...");
        Elements images = doc.getElementsByTag("img");

        for(Element src: images){
            System.out.println("src: " + src.attr("abs:src"));
        }

    }
}
