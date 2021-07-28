package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /*
     반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view를 조회하지 않고 data를 직접 response에 넣어준다
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username ={}, age={}", memberName, memberAge);
        return "ok";
    }

    /*
    @HTTP 파라미터 이름이 변수이름과 같으면 파라미터 이름 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={},age={}", username, age);
        return "ok";
    }

    /*
    만약 String, int 등의 단순 타입이면 @RequestParam도 생략가능 ->너무과하긴함
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={},age={}", username, age);
        return "ok";
    }

    /*
    age는 지금 required가 false로 되어있기 때문에 아무것도 입력하지 않아도 정상적으로 mapping됨. 이때 int타입인데 아무것도 입력하지 안흥면 null이 들어가게 되는데
    int에 null을 집어넣는 것은 불가능하기 때문에 객체타입인 Integer로 mapping을 해놨음.
     */

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ) {
        log.info("username={},age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /*파라미터에 값이 없는 경우 defaultValue가 적용되기 때문에 required는 의미가 없다.
      즉, required= true라고 해서 반드시 필요한 필드임을 설정해줬지만 아무것도 입력을 안했을때 자동으로 defaultValue가 할당되기때문에
      아무것도 안 적어도 정상적으로 mapping이 된다는 의미다.(원래라면 required=true로 되어있고 아무 인자도 들어오지 않으면 error가 떠야 한다)
     */

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={},age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /*그냥 requestParam을 하나씩 받는거랑 전혀 다른게 없는 것 같음.
    왜냐면 그냥 url도 http://localhost:8080/request-param-map?username=zaidKang&age=23 이렇게 똑같이 입력했음. 그러니깐 내가볼때 그냥 param을 map형식으로 받는 방법에 지나지 않음
    *주의할점: 파라미터의 값으로 1개만 오는 것이 확실하면 Map을 사용해도 되지만 그렇지 않으면 MultiValueMap을 사용하자.
    */


    //----------------------------object로 param 받기 시작---------------------------------------------------------

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={},age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /*
    @ModelAttribute 도 생략 가능한데 String 과 int같은 단순 타입이면 @RequestParam으로 간주하고, argument resolver로 지정해둔 타입 외에는 자동으로 @ModelAttribute로 간주한다
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
