package per.coc.dice.roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/excel")
public class excelController {

    @RequestMapping(value = "excelShow2")
    @ResponseBody
    public List<Integer> excelShow2(int rangeSpeed) {
        System.out.println(rangeSpeed);

        Random random = new Random();//默认构造方法
        List<Integer> list2 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int i2 = i;
            list2.add(i2*rangeSpeed);
        }
        System.out.println(list2);
        return list2;
    }

}
