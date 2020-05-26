package per.coc.dice.roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.coc.dice.roller.webScoket.WebSocketTest;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

@Controller
@RequestMapping("/system")
public class RollerController {

    @RequestMapping("roll")
    public String roll(){
        return "roll";
    }

    @RequestMapping("kproll")
    public String kproll(){
        return "kproll";
    }

    @RequestMapping("room")
    @ResponseBody
    public Integer goRoom(HttpServletRequest request, Session session, Integer room){
        Integer count = WebSocketTest.getOnlineCount();
        request.setAttribute("count", count);
        return count;
    }

    @RequestMapping("memberNumber")
    @ResponseBody
    public Integer memberNumber(){
        Integer count = WebSocketTest.getOnlineCount();
        return count;
    }

    @RequestMapping("sendInfo")
    @ResponseBody
    public void sendInfo(){
        String message = "系统提示：你被杀了";
        try {
            WebSocketTest.sendInfo(message);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
