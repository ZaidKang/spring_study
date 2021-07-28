package hello.springmvc.basic;

import lombok.Data;

@Data
public class HelloData {
    private String username;
    private int age;
}

//cf) @Data 어노테이션은 게터,세터,투스트링 등을 자동으로 적용해줌
