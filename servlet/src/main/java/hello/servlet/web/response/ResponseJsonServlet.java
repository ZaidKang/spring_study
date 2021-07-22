package hello.servlet.web.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");

        HelloData data = new HelloData();
        data.setUsername("kim");
        data.setAge(26);

        /*
        helloData는 약간 enum객체잖아. 근데 우리가 지금 이 class에서 반환을 json으로 해준다매 그럼 잭슨 라이브러리를 사용해서
        데이터가 저장되어있는 객체인 hellodata를 json(이게 곧 string인듯)으로 바꿔주는거지

        */
        String result = objectMapper.writeValueAsString(data);

        response.getWriter().write(result);
    }
}
