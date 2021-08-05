package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//    @PostMapping("/add")
//    public String addItemV4(Item item) {
//        itemRepository.save(item);
//        return "basic/item";
//    }

    //근데 버전이 모두 종료되기는 했는데 난 솔직히 왜 이렇게 까지 생략을 해가면서 버전업을 해야되는건지 모르겠음
    //내가볼때 김영한도 그냥 방법의 하나로 가르쳐주는 것 뿐이지 이게 크게 중요하진 않은것 같음. 그거 몇줄이라고
    //그냥 full code로 갈기는게 나은것 같음. 의미도 제일 잘 와닿고

    // 지금 addItemV1~addItemV4 이 부분은 문제가 생겨서 PRG Post/Redirect/Get 방식으로 해결해보자

//    @PostMapping("/add")
//    public String addItemvV5(Item item) {
//        itemRepository.save(item);
//        return "redirect:/basic/items/" + item.getId();
//
//        // 아니 이게 돼? 되네 ;; 이건 "redirect:/basic/items/{itemId}"; 랑 다른게 지금 여기에선 get으로 가져와야 돼고 저기서는 애초에 itemId로 받아온거니깐 그 차이인듯
//
//        //        Long itemId = item.getId();
////        return "redirect:/basic/items/{itemId}";
//        // 이건 왜 안되니ㅑ 또?
//    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    /*
    -basic/editForm에서 저장을 누르면 밑에 post방식이 호출돼야하는데 이게 상식적으로 어떻게 되지? 싶었는데 여기도 th:action 하고 action field에 값이 없네
    -그러면 현재 위치한 url로 post방식으로 호출이 될테니깐 밑에 api 가 호출되겠지
     */
//    @PostMapping("/{itemId}/edit")
//    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
//        itemRepository.update(itemId, item);  //처음에는 이 로직을 여기서 짜버림. ㅋㅋ 근데 알고보니깐 repository에 이런 메소드가 이미 정의가 되어있었넹;ㅎㅎ
//        return "basic/items";
//
// }
        
        /*
        맨처음에 redirect 해서 띠용 싶었는데 이게 어쩔수가 없는 구조네
        왜냐면 생각을 해보면 수정 버튼을 눌르면 이제 수정된 정보가 딱 item 상세 로 떠야 되는데 여기서 만약 그냥 basic/item html 파일을 호출한다? 그러면 model 에 itemid를 넣어서 호출할 수가 없잖아
        그냥 완전 정적 파일을 호출하는것 그 이상 그 이하도 아니지
        그럼 동적으로 id를 넣어서 호출을하려면? 상세 페이지를 띄우는 mapping uri로 redirect하면 원큐에 해결되지

        물론 근데 방법은 있을 것 같기도 함. 정적 html 에 모델을 우겨넣을수만 있으면 되는걸테니깐

        근데 왜 그냥 "basic/html" 단순 html 파일만 return해줬는데 동작하는거냐?
        유일한 가능세계는 스프링에서는 @ModelAttribute 가 있으면 자동으로 model에 addAttribute를 시켜준다는 건데 이것도 위를 참고하면
        model이 넘어올 경우나 그런거고

         */


    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    //일단 redirect version에서도 되기는 함
    //이 부분은 확실하게 알려면 일단 다 해보고 강의를 쫙 들으면서 체크해보면 될듯듯
}
