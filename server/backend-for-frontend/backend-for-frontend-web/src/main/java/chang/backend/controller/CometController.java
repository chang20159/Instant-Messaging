package chang.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by chang on 2017/3/18.
 */
@Controller
public class CometController {

    private static final String TARGET_ORIGIN = "http://localhost:8000";

    /**
     * Ajax长轮询 长连接采取的是服务端阻塞（保持响应不返回）
     * @param requestTime 开始请求时间
     * @throws Exception
     */
    @RequestMapping(value="/ajax/long-polling", method= RequestMethod.GET)
    @ResponseBody
    public String ajaxLongPolling(long requestTime) throws Exception{
        Random random = new Random();
        int num;
        long responseTime;
        while(true){
            Thread.sleep(300); //休眠，模拟业务处理时间
            num = random.nextInt(100);
            if(num > 20 && num < 30){ //有数据变化，返回
                responseTime = System.currentTimeMillis();
                return String.format("response time: %s, request time: %s,result: %d", responseTime,requestTime,num);
            }else{
                Thread.sleep(1000);
            }
        }
    }


    /**
     * iframe长连接
     * @param requestTime 开始请求时间
     * @throws Exception
     */
    @RequestMapping(value="/iframe/long_connection", method= RequestMethod.GET)
    public String iframeLongConnection(HttpServletResponse response,long requestTime) throws Exception{
        response.setHeader("content-type","text/html");
        PrintWriter writer = response.getWriter();
        Random random = new Random();
        int num;
        String responseData;
        long responseTime;
        while(true){
//            Thread.sleep(new Random().nextInt(300)); //休眠，模拟业务处理时间
            num = random.nextInt(100);
            if(num > 20 && num < 90){ //有数据变化，返回
                responseTime = System.currentTimeMillis();
                responseData = String.format("response time: %s, request time: %s,result: %d", responseTime, requestTime, num);
                writer.print(String.format("<script>parent.window.postMessage('%s<br/>','%s');</script>", responseData, TARGET_ORIGIN));
            }else{
                Thread.sleep(100);
            }
        }
    }



}
