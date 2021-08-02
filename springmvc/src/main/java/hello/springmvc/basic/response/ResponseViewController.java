package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mv = new ModelAndView("response/hello").addObject("data", "hello!!");

        return mv;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "여긴 data의 값이 들어간다");
        return "response/hello";  //만약에 @ResponseBody가 붙어있었으면 이게 그냥 string값으로 나갔겠지, view resolver를 거치지 않고
    }

    //어떻게 void인데 view로 data를 전달하지? 했는데 url에 답이 있기는 한데 스프링에서 이게 어떻게 가능한지 그 원리는 정확히는 모르겠는데
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello~~");
    }
    //일단 뭐 노트를 보면 @controller가 붙어있는데 HttpServletResponse나 outputStream(writer)같은 Http 메시지 바디를 처리하는 파라미터가 없으면 요청 url을
    //참고해서 논리 view 이름으로 사용한다네
    //만약 요청 url:/response/hello
    //실행:templates/response/hello.html

    //->이 방식은 명시성이 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없어서 권장하지 않는다고 하네 역시 내가 적어놓고도 ㅈㄴ 의미가 와닿지가 않았음

}
