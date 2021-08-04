package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    그래서 이 어노테이션 덕분에 생성자를 만드는 코드 자체도 생략된거임
    그러니깐 정리하자면,,,,

    @RequiredArgsConstructor만 붙히면 생성자가 1개기 때문에 위의 코드가 완성되면서 dependency injection이 된다는 말씀
    즉 저 어노테이션만 붙으면 아무것도 없어도 생성자를 만들어주면서 autowired로 dependency injection도 된다.

     */

    //전체 상품 목록
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

    //id에 해당하는 상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute(item);
        return "basic/item";
    }

    //상품 등록 폼
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }
//
//    @PostMapping("/add")
//    public String addItemV1(@RequestParam String itemName,
//                            @RequestParam int price,
//                            @RequestParam Integer quantity,
//                            Model model) {
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//
//        itemRepository.save(item);
//
//        model.addAttribute("item", item);
//        return "basic/item";
//    }
//
//    //@RequestParam 으로 변수를 하나하나 받아서 Item 을 생성하는 과정은 불편했다.
//    //이번에는 @ModelAttribute 를 사용해서 한번에 처리해보자.
//    //어차피 html에서 post방식으로 알아서 넘어오는데 이건 뭔 개소리임?
//
//    @PostMapping("/add")
//    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
//        itemRepository.save(item);
//        //model.addAttribute("item",item); //스프링에서 자동으로 추가해주는 부분이라 생략이 가능하다
//
//        return "basic/item";
//    }

    /*
    지금 return 형식이 모두 view type이라 postman방식이 아니라 결국은 html에서 해결해야 되는 상황이고
    html을 addItem version에 따라서 계속 구분하지는 않을텐데 어떻게 이렇게 객체로 하는거지? 싶었는데

    설명 부분을 보면(사실 이미 배운 내용이기는 함)
    @ModelAttribute는 Item 객체를 생성하고, 요청 파라미터로 넘어온 값을 프로퍼티 접근법(setXxx)로 알아서 자체 입력해준다.
    또한 같이 넘어오는 model에 @ModelAttribute로 지정한 객체를 자동으로 넣어준다.
     */


    /*
    -@ModelAttribute 의 네임 속성 생략 가능
    -버전 2와 마찬가지로 model.addAttribute(item); 부분은 스프링에서 알아서 해주는 부분이라 생략 가능
    -생략시 스프링은 model에 저장되는 name으로 클래스명 첫글자만 소문자로 바꿔서 등록해준다.
    생략되는 model.addAttribute(item)에서 item 부분이 클래스명 Item에서 I만 i로 바꿔서 등록해준다.
//     */
//    @PostMapping("/add")
//    public String addItemV3(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "basic/item";
//    }


    /*
    -@ModelAttribute 자체를 생략 가능
    -이게 김영한이 말했던 과하다는 생략이군. 뭔 객체를 받아온다는 어노테이션까지 생략을 해버리면 이거 나원참
     */

    //실제 상품 등록 로직
   @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //근데 버전이 모두 종료되기는 했는데 난 솔직히 왜 이렇게 까지 생략을 해가면서 버전업을 해야되는건지 모르겠음
    //내가볼때 김영한도 그냥 방법의 하나로 가르쳐주는 것 뿐이지 이게 크게 중요하진 않은것 같음. 그거 몇줄이라고
    //그냥 full code로 갈기는게 나은것 같음. 의미도 제일 잘 와닿고


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }
}
