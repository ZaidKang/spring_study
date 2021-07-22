package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private String username;
    private int age;


    public Member(){

    }

    //id는 레포지토리가 자동 할당하도록 설정
    public Member(String username,int age){
        this.age=age;
        this.username = username;
    }

}
