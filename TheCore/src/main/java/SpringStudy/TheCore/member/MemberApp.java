package SpringStudy.TheCore.member;

import SpringStudy.TheCore.Appconfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        Appconfig appconfig = new Appconfig();
//        MemberService memberService = appconfig.memberService();


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member1 = new Member(2L, "zaid kang", Grade.VIP);
        memberService.join(member1);

        Member foundedmember = memberService.findMember(2L);
        System.out.println("foundedmember = " + foundedmember.getName());
        System.out.println("member1 = " + member1.getName());

    }
}
