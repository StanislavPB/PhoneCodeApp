package PhoneCode.App.controller;


import PhoneCode.service.DetectCountry;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("codes")

public class Controller {
    private final DetectCountry detectCountry;

    private List <String> messages;


    @GetMapping
    public List<String> list() {
        return messages;
    }

    @PostMapping

    public List<String> checkCountry (@RequestBody String phoneNumber) throws IOException {

        messages.clear();

        String countryArea = "+"+phoneNumber.substring(0, (phoneNumber.length()-7));

        Map<String, String> country =  detectCountry.getCountry(countryArea);


        country.forEach((key, value) -> {

            String message = "Your phone number - " + phoneNumber + "  Country code - " + key
                    + "  Country is " + value;

            messages.add(message);
        });
        
        return messages;
    }

  }
