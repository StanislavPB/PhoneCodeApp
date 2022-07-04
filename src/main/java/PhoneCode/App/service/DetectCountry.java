package PhoneCode.App.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DetectCountry {

    private final GetMapFromWebPage getMapFromWebPage;

    public Map<String, String> getCountry(String codeForCheck) throws IOException {

        String url = "https://en.wikipedia.org/wiki/List_of_country_calling_codes";

        var codeList = getMapFromWebPage.getDataFromPage(url);

        Map<String, String> country = new HashMap<>();

        for (Map.Entry<String,String> entry : codeList.entrySet()) {

            String codeForCheck1= codeForCheck.substring(0,codeForCheck.length()-1);
            String codeForCheck2= codeForCheck.substring(0,codeForCheck.length()-2);
            String codeForCheck3= codeForCheck.substring(0,codeForCheck.length()-3);

            boolean check = Objects.equals(entry.getKey(), codeForCheck);
            boolean check1 = Objects.equals(entry.getKey(), codeForCheck1);
            boolean check2 = Objects.equals(entry.getKey(), codeForCheck2);
            boolean check3 = Objects.equals(entry.getKey(), codeForCheck3);



            if (check){
                country.put(codeForCheck,entry.getValue());}

            if (check1){
                country.put(codeForCheck1,entry.getValue());}

            if (check2){
                country.put(codeForCheck2,entry.getValue());}

            if (check3){
                country.put(codeForCheck3,entry.getValue());
            }

        }

        if  (country.isEmpty()) {
            country.put("Error code"," Error! Country has not detected!");
        }

        return country;
    }
}