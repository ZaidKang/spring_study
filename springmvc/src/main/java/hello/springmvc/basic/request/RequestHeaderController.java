package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @RequestHeader("connection") String connection,//만약 여기서 host1이라고 하면 whitelabe뜸. host1이 header에 존재하지 않는다고. 당연하지 ㅇㅇ
                          @CookieValue(value = "myCookie", required = false) String cookie) // 만약 여기서 required field가 true로 되어있으면 반드시 해당 parameter가 헤더에서 값이 들어와줘야 되는데 만약 값이 들어오지 않으면 whitelabel뜸
    {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("header connection={}", connection);
        log.info("myCookie={}", cookie);


        //-----------------------------------------------------------------------------------------------------------------
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("keyA", "value1");
        map.add("keyA", "value2");

        List<String> values = map.get("keyA");
        log.info("멀티밸류맵 실험 {}", values);

        return "ok";
    }


}
