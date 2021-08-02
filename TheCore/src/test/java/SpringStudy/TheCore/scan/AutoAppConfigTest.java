package SpringStudy.TheCore.scan;

import SpringStudy.TheCore.AutoAppConfig;
import SpringStudy.TheCore.member.MemberService;
import SpringStudy.TheCore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        //실제 AutoAppconfig를 확인해보면 @Configuration 이랑 @ComponentScan밖에 없는데 AutoAppConfig을 컨테이너로 넣으면
        //-> AnnotationConfigApplicationContext는 동일한데 설정정보로 AutoAppConfig를 넘겨주냐의 차이
        //잘 동작하는지 확인해보자

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);


    }
}
