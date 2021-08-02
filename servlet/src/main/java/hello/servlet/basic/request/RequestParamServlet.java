package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
1.파마리터 전송 기능
http://localhost:8080/request-param?username=hello&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회 - start");

        req.getParameterNames().asIterator().forEachRemaining(paraName -> System.out.println(paraName + "=" + req.getParameter(paraName)));

        System.out.println("전체 파라미터 조회 - end");
        System.out.println("");

        System.out.println("단일 파라미터 조회");
        String username = req.getParameter("username");
        String age = req.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("");


        System.out.println("이름이 같은 파라미터에 여러값이 들어왔을 경우");
        String[] names = req.getParameterValues("username");
        for (String name : names) {
            System.out.println("username = " + name);
        }


        //허전하니깐 응답부분

        resp.getWriter().write("ok");

    }
}
