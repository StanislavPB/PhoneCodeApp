package PhoneCode.App.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExitController {

    @GetMapping("/exit")
    public void getExit() {

        System.exit(0);
    }
}
