package hello.springmvc.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        //log.info("info log={}", name);
        //name이 {}안에 박혀서 출력이 되네 신기하노

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        log.debug("String concat log= " + name);
        //스프링에서는 이렇게 쓰면 log를 쓰기도 전에 a+b연산을 해버리기 때문에 오버헤드가 큼. 이런 방식으로 하면 안됨.


        return "ok";

    }

}
