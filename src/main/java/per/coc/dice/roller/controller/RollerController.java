package per.coc.dice.roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RollerController {

    @RequestMapping("roll")
    public String helloWorld(){
        return "roll";
    }
}
