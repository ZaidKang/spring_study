package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertThat(savedItemA).isEqualTo(findItemA);
        Assertions.assertThat(savedItemB).isEqualTo(findItemB);
        //Assertions.assertThat(savedItemA).isEqualTo(findItemB); -> 역시나 정상적으로 fail이 호출되는구만


        //문제 됐던 잘못된 코드
        //Assertions.assertThat(savedItemA.equals(findItemA));

    }

    @Test
    @DisplayName("레포지토리에서 모든 아이템 가져오기")
    void findAll(){
        //given
        Item itemA = new Item("itemA", 2000, 50);
        Item itemB = new Item("itemB", 3000, 800);
        Item itemC = new Item("itemC", 4000, 900);
        Item itemD = new Item("itemD", 5000, 1000);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);
        itemRepository.save(itemD);

        //when
        List<Item> findList = itemRepository.findAll();


        //then
        Assertions.assertThat(findList.size()).isEqualTo(4);
        Assertions.assertThat(findList).contains(itemA, itemC); //이 부분은 몰랐네잉

    }

    @Test
    @DisplayName("리포지토리에 있는 아이템 업그레이드 하기")
    void updateItem(){
        //given
        Item itemA = new Item("itemA", 2000, 50);
        Item findItemA = itemRepository.save(itemA);

        //when
        Item updateParam = new Item("updatedItem", 30000, 1000);
        itemRepository.update(findItemA.getId(),updateParam);

        //then
        Assertions.assertThat(findItemA.getItemName()).isEqualTo(updateParam.getItemName());
        //-> 이건 객체 자체를 Assertions하면 안되는게 updateParam에는 새로운 itemId가 없는게 당연한거잖아. 오히려 없는게 정상임
        //그래서 김영한은 아예 3개를 비교해버리네
        Assertions.assertThat(findItemA.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(findItemA.getQuantity()).isEqualTo(updateParam.getQuantity());
    }



}
