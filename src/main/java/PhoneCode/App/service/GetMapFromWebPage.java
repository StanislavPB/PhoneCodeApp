package PhoneCode.App.service;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
public class GetMapFromWebPage {

    final private FillMap fillMap;

    public Map<String, String> getDataFromPage(String url) throws IOException{

        Map<String, String> codesMap = new HashMap<>();

        Document document = Jsoup.connect(url).get();
        Element table = document.select("table.wikitable").get(1);
        Elements body = table.select("tbody");
        Elements rows = body.select("tr");

        codesMap = fillMap.fillMap(rows);

        return codesMap;
    }


}

