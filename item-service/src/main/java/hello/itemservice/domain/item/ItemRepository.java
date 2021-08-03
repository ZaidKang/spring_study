package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        //이거 은근 헷갈리네. 그니깐 예를 들어 item이라는 객체가 들어온다고 치면 그 객체를 바로 써도 되는건지 아니면
        //들어온 값으로 다시 함수 내에서 객체를 생성해줘야하는 건지

        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    public void update(Long itemId, Item updateParam) {
        Item item = store.get(itemId);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());

        //맨 처음에 난 당연히 save할때도 return 해주길래 update할때도 item return 해줘야할줄 알고 return 해줬음
        //근데 안하네 씹련 ㅋ 근데 이건 뭐 딱히.. 설계 스펙의 차이일뿐이라서 ㅇㅇ...
        //190p
    }




    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
