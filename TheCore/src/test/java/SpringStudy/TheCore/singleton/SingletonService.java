package SpringStudy.TheCore.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    //싱글톤을 적용하기 위해서는 저렇게 private static final 에다가 instance로 받은다음 외부에서 접근할 때는 getter를 통해서만 접근할 수
    //있도록 해줬어야 함. + private으로 생성자를 저렇게 해주는 것도 해야됨됨

    public void logic() {
        System.out.println("싱글톤서비스로직 실행 part");
    }
}
