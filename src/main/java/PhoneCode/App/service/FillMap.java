package PhoneCode.App.service;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class FillMap {

    public Map<String, String> fillMap(Elements rows) {

        Map<String, String> codesMap = new HashMap<>();

        for (Element row : rows) {
            Elements columns = row.select("td");

            if (columns.size() > 2) {
                String name = columns.get(0).text();
                String code = columns.get(1).text();

                String[] arrayOfDoubleCode = code.split(",");;

                for (String eachCode : arrayOfDoubleCode) {
                    if (eachCode.contains("[")) {  eachCode = eachCode.substring(0,eachCode.indexOf("["));}
                    if (eachCode.charAt(0)=='+') {
                        eachCode = eachCode.replaceAll("\\s+","");
                        if (eachCode.equals("+1")) {name = "USA /Canada";}
                        codesMap.put( eachCode.trim(),name);}
                }
            }
        }
        return codesMap;
    }
}

