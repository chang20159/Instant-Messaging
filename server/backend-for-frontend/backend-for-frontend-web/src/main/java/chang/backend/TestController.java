package chang.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chang on 2017/3/18.
 */
@Controller
public class TestController {
    @RequestMapping(value = "test")
    @ResponseBody
    public  String baseType(Integer age){
        return "age:"+age;
    }
}
