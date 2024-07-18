import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        WebCrawlerForBrokenLinks crawler = new WebCrawlerForBrokenLinks();
        crawler.startCrawling("https://www.nytimes.com/international/");
        crawler.startCrawling("https://www.nytimes.com");
        crawler.shutdown();

//        ArrayList<WebCrawler> spiders = new ArrayList<>();
//        spiders.add(new WebCrawler("https://abcnews.go.com",1));
//        spiders.add(new WebCrawler("https://www.nytimes.com/international/",2));
////        spiders.add(new WebCrawler("https://www.nytimes.com",3));
//
//        for(WebCrawler wc: spiders){
//            try{
//                wc.getThread().join();
//            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }

    }
}
