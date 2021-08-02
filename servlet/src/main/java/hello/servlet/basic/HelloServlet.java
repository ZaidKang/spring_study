package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //ctrl + O 누르면 뜸
    //이 서블릿이 호출되면 이 서비스 메소드가 호출이 됨.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");

        //서블릿에서 request랑 response 생성해주는데 이게 뭔지 한번 찍어보자
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        /*localhost:8080/hello?username=kim 이렇게 요청을 하면
        쿼리 parameter방식으로 요청한것.
        서블릿에서 쿼리 parameter를 처리하는 방법은 하단 참고
        */

        String username = req.getParameter("username"); //url에 입력하는 부분을 받아오는 바이브
        System.out.println("username = " + username);
        //이건 단순히 query parameter찍어보기 위한 코드이고

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        //이건 헤더에 들어가는 정보

        resp.getWriter().write("hello" + username);  //http 메시지 바디에 데이터가 들어감

    }


}
