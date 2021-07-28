package hello.springmvc.basic.requestmapping;

import org.apache.tomcat.util.http.parser.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    //공부하다가 발견한건데 아니 뭐 발견이라기 보다는 당연한거긴 한데 만약에 getmapping, postmapping이런식으로 다른 mapping 어노테이션이여도
    //그 안에 mapping 되는 url이 다르면 에러뜸.
    @GetMapping("/mapping-get-v2")
    public String mappingGetv2() {
        log.info("mappingGetv2, 메소드가 아닌 직관성을 위한 GetMapping");
        return "실제로 GetMapping에는 RequestMapping 어노테이션이 들어있다";
    }


    @GetMapping("mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("패쓰베리어블 테스트입니다. {}에 data값이 들어가야됩니다", data);
        return "ok";
    }

    @GetMapping("mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath log test, 유저아이디={} 이고 주문id는 ={} 이다", userId, orderId);
        return "ok";
    }

    //특정 파라미터가 있거나 없는 조건을 추가할 수 있다. 잘 사용하지는 않는다
    @GetMapping(value = "/mapping-param", params = "mode-debug")
    public String mappingParam() {
        log.info("특정 파라미터 조건 매핑 성공");
        return "ok";
    }

    //특정 헤더 조건 매핑
    //바로 위의 파라미터 매핑의 http 헤더버전
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //요청의 미디어 타입으로 조건 매핑하는 경우
    //기반은 http 요청의 content-type 헤더 기반
    //content-type은 보내는 자원의 형식을 명시하기 위해 헤더에 실리는 정보이다.
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    //기반은 Accept헤더
    //accept 헤더는 클라이언트가 이해 가능한 컨텐츠 타입이 무엇인지 알려준다.
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
    //근데 궁금한게 있는데 왜 자꾸 교보재에 "Postman으로 테스트 해야 한다."라고 있는거임?
    //이게 뭔소린지 모르겠음
}


