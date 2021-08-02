package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {

        response.getWriter().write("ok");

    }


    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /*
    지금 헷갈리는거
    1.언제는 IOException던지고 언제는 안던짐?
    2.v1같은 경우는 HttpServletResponse를 인자로 받는데 Entity는 인자로 받지 않는것도 그 기준을 잘 모르겠음
     */

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);

        /*
        아까도 ResponseEntity로 json을 객체로 바꾸는게 request쪽에 있었는데 지금이랑 다른거는 그때는 클라쪽에서 여기
        data로 값을 넣어줄거를 보내줬던거고 여기서는 응답에만 집중하기 위해서 request없이 url만 호출되면 그냥 데이터 쏘는거고

        지금 보면 객체를 생성한다음에 ResponseEntity를 return 해주고 있잖아.
        이때 HTTP 메시지 컨버터가 helloData 객체를 json으로 변환한다음 내보내줌
         */

    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData data = new HelloData();
        data.setUsername("zaidKang");
        data.setAge(26);

        return data;
    }

    /*
    ResponseEntity는 Http 응답코드를 파라미터로 넣어서 직접 설정할 수 있는데 ResponseBody는 진짜
    말 그대로 http body에 콱 넣어버리는 거기 때문에 상태메시지 설정하기가 까다롭다

    그래서 한것이 바로 @ResponseStatus(HttpStatus.Ok)이다. 이를 통해 응답 코드를 설정할 수 있다.
    물론 애노테이션이기 때문에 응답코드를 동적으로 변경할 수는 없음. 만약 프로그램 조건에 따라서 동적으로 변경하려면 위에서 사용한 ResponseEntity를 사용하면 됨
     */


}
