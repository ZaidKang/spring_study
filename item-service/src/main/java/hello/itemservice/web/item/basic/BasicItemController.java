package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    /*
    원래는 private final ItemRepository itemRepository;
    해주고 생성자를 하나만들어줘야 됨 . 이렇게

    public BasicItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    근데 이거 예전에 입문강의에서 배웠지만 dependency를 injection해줘야 되잖아 그래서
    @Autowired로 dependency를 injection해줄수 있게 해줘야겠지. 근데 입문강의에서 배웠듯이(배운거 같기는 한데 기억은 안남 ㅋ)
    만약에 생성자가 1개 있으면 스프링에서는 생략이 가능하대 그래서 생략한거고

    @RequiredArgsConstructor는 lombok 어노테이션인데 이걸 붙히면 final이 붙은애를 가지고 저 생성자를 만들어준대
    그러니깐 정리하자면,,,,

    @RequiredArgsConstructor만 붙히면 생성자가 1개기 때문에 위의 코드가 완성되면서 dependency injection이 된다는 말씀

     */


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @PostConstruct //test용으로 레포지토리에 미리 넣어놓는 거임
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}
