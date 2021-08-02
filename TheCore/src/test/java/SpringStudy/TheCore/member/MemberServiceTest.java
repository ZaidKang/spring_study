package SpringStudy.TheCore.member;

import SpringStudy.TheCore.Appconfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        Appconfig appconfig = new Appconfig();
        memberService = appconfig.memberService();
    }

    @Test
    void join() {
        //given(이런게 주어졌을때)
        Member member = new Member(1L, "ZaidKang", Grade.Basic);

        //when(이렇게 했을때)
        memberService.join(member);
        Member findMember = memberService.findMember(2L);

        //then(이렇게 나와야 한다)
        Assertions.assertThat(member).isEqualTo(findMember);

    }


}
