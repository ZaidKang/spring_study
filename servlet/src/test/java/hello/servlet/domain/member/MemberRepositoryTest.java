package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("저장테스트")
    void save(){

        //given
        Member member = new Member("Zaid", 23);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Assertions.assertThat(savedMember.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("전체 조회 테스트")
    void findAll(){
        //given
        Member member = new Member("player1", 23);
        Member member2 = new Member("player2", 24);

        memberRepository.save(member);
        memberRepository.save(member2);

        //when
        List<Member> newList = memberRepository.findAll();

        //then
        Assertions.assertThat(newList.size()).isEqualTo(2);
        Assertions.assertThat(newList).contains(member, member2);
    }

}
