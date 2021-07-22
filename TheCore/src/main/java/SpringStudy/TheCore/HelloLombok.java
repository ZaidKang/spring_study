package SpringStudy.TheCore;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("sdfsadf");

        String name = helloLombok.getName();
        System.out.println("name = " + name);


        HelloLombok helloLombok1 = new HelloLombok();
        helloLombok1.setName("이것이 toString의 쓰임새인가");

        System.out.println("helloLombok1 = " + helloLombok1);

    }

    //그니깐 롬복은 게터 세터를 자동으로 해주는 annotation이라고 생각하면 됨
    //cf) 생성자 관련해서도 롬복에서 지원해줌
}
