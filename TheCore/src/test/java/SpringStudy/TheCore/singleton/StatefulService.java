package SpringStudy.TheCore.singleton;

public class StatefulService {
    //실무에서 정말 많이 발생하는 문제점(싱글톤 방식을 사용했을 때의,)

    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제
    }

    public int getPrice() {
        return price;
    }
}
