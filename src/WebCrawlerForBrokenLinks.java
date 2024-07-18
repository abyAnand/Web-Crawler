import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebCrawlerForBrokenLinks {


    private final Set<String> visitedLinks = new HashSet<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void startCrawling(String startUrl) {
        executorService.submit(() -> crawl(startUrl));
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Executor service did not terminate..");
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(String url) {
        if (!visitedLinks.add(url)) {
            return;
        }

        try {
            Connection.Response response = Jsoup.connect(url).execute();
            Document doc = response.parse();

            System.out.println("URL: " + url);
            System.out.println("Last Updated: " + response.header("Last-Modified"));

            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String absUrl = link.absUrl("href");
                executorService.submit(() -> crawl(absUrl));
            }
        } catch (IOException e) {
            System.err.println("Broken link: " + url + " - " + e.getMessage());
        }
    }
}
