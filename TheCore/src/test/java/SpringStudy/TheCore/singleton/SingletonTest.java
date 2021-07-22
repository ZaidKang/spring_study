package SpringStudy.TheCore.singleton;

import SpringStudy.TheCore.Appconfig;
import SpringStudy.TheCore.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        Appconfig appconfig = new Appconfig();

        MemberService memberService1 = appconfig.memberService();

        MemberService memberService2 = appconfig.memberService();

        //호출할때마다 객체를 생성해서 직접 찍어봤을 때 참조값이 다른 것을 확인할 수 있음.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService = " + singletonService);
        System.out.println("singletonService2 = " + singletonService2);


        Assertions.assertThat(singletonService).isSameAs(singletonService2);
        //same : 자바 객체의 instance == 비교
        //equal: 자바 equal method 오버라이드

        //? 이게 성공하면 안되는거잖아. 아니 애초에 singletonTest객체를 저렇게 2개를 생성해준거 부터 저러면 안됨
        //ㅇㅇ 내가 잘못적은거였음. private 생성자로 막아놨으니깐 외부에서 접근할때는 getInstance사용해서 저런식으로 접근해줘야지
        //이렇게 해주니깐 같은 객체가 나옴


    }

    @Test
    @DisplayName("스프링 컨테이너가 싱글톤으로 작동하는지 test해보자자")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //Assertions.assertThat(memberService1).isEqualTo(memberService2);
        Assertions.assertThat(memberService1).isSameAs(memberService2);

        //뭐임? 둘다 맞게 나오는데 ㅋ,ㅋ

    }


}
