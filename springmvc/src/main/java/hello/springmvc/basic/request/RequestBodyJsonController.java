package hello.springmvc.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper(); //jakson 라이브러리인 objectMapper을 사용해서 String으로 이루어진 JSON 데이터를 자바 객체로 사용할 수 있게끔 한다.

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response
    ) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username= {},age= {}일건데 이건 객체형식으로 받아져 오겠지", data.getUsername(), data.getAge());

        response.getWriter().write("ok");

    }


    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("입력은 json으로 들어왔지만 스프링 내부에서 객체로 변환해서 처리한 것을 확인 할 수 있다. 회원명={}, 나이={}", data.getUsername(), data.getAge());
        return "ok";
    }

    //나는 json 데이터를 객체로 읽어오는 것 자체로 되게 편하다고 생각했는데 여기서는 이것도 번거롭대(json을 objectmapper로 객체로 변환한다음에 사용해줘야 하잖아)
    //생각해보면 modelattribute..잠만 뇌정지오는데

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData data) {
        log.info("이게 된다고? 회원명={}, 나이={}", data.getUsername(), data.getAge());
        return "ok";
    }

    //아 맞네 modelattribute도 그거였지 어노테이션 적어주면 바로 객체형식으로 쓸수있었잖아 저렇게 objectMapper로 안해도
    //근데 그럼 궁금한게 modelattribute랑 requestbody차이점이 뭐임?

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData data = httpEntity.getBody();
        log.info("username={},age={} by using HttpEntity", data.getUsername(), data.getAge());
        return "ok";
    }
    /*
    1.@RequestBody 는 생략불가능(생략됐을시 modelAttribute가 default이기 때문에)
    2.HttpMessageConverter가 사용 -> MappingJackson2HttpMessageConverter

    1.@ResponseBody를 적용하면 마찬가지로 HttpMessageConverter가 사용되고 MappingJackson2HttpMessage가 마찬가지로 적용됨
     */


    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(HttpEntity<HelloData> httpEntity) {
        HelloData data = httpEntity.getBody();
        log.info("username={},age={}", data.getUsername(), data.getAge());
        return data;
    }
    //객체를 return하게 되면 실제로 postman상에서 어떻게 응답이 올지 궁금했었는데 json형태로 오네


}
