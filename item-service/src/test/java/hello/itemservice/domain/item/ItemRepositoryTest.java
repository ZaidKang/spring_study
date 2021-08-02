package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("회원이 레포지토리에 제대로 저장됐는지 test")
    void saveTest(){
        //given
        Item itemA = new Item("itemA", 2000, 50);
        Item itemB = new Item("itemB", 3000, 800);

        //when
        Item savedItemA = itemRepository.save(itemA);
        Item savedItemB = itemRepository.save(itemB);

        Item findItemA = itemRepository.findById(savedItemA.getId());
        Item findItemB = itemRepository.findById(savedItemB.getId());

        //then
        Assertions.assertThat(savedItemA, equals(findItemB));
        Assertions.assertThat(savedItemB.equals(findItemB));

        Assertions.assertThat(savedItemA.equals(findItemB));

        Assertions.assertThat(savedItemA.);
        Assertions.assertThat(savedItemA.)

        System.out.println("findItemB = " + findItemB);
        System.out.println("savedItemA = " + savedItemA);


    }



}
