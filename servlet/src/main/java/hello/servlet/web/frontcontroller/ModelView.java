package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName; //view의 논리적이름
    private Map<String, Object> model = new HashMap<>(); //직접 만든 모델에 해당

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setModel(Map<String,Object> model){
        this.model = model;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
