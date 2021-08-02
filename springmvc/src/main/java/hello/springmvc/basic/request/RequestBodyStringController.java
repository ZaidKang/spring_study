package hello.springmvc.basic.request;

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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    /*
    inputStream(reader): http 요청 메시지 바디의 내용을 직접 조회
    outputStream(Writer): http 응답 메시지 바디의 내용을 직접 작성
     */

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    /*근데 뭐 인자로 inputStream을 받아오는 것 까지는 직관적으로 어색하지는 않은 수준인데 같이 받아오는게
    Writer인게 뭐 와닿지는 않네
    ->inputstream이랑 streamutils이랑 copytostring 뭐 이런거 다 조사해봐야할 듯(서블릿 진영)

     */

    @PostMapping("request-body-string-test-by-zaidkang")
    public void requestBodyStringTest(InputStream inputStream, OutputStream outputStream) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("test by zaid Kang, message should be = {}", messageBody);

        outputStream.write(2);
    }
    //이거 test해본건데 왜 postman에서는 1로 응답나오냐?

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("messageBody test ={}", httpEntity.getBody()); //이렇게 해도 바로 처리 되나?
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }

    /*
    -요청 파라미터를 조회하는 기능: @RequestParam, @ModelAttribute
    -Http 메시지 바디를 직접 조회하는 기능: @RequestBody

    이로써 이렇게 정리가 딱 됐군군
     */

    //여태까지는 request에서 단순텍스트를 조회했던 거고 이제는 json을 조회해보자
    //근데 json도 결국 인식 자체는 스트링으로 하지 않나? 무슨 차이가 있지


}
