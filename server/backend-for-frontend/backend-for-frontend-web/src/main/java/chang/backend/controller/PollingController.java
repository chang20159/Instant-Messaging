package chang.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by chang on 2017/3/28.
 */
@Controller
public class PollingController {
    /**
     * Ajax轮询
     * @param response    HttpServletResponse
     * @param requestTime 开始请求时间
     * @throws Exception
     */
    @RequestMapping(value="/ajax/polling", method= RequestMethod.GET)
    public void ajaxPolling(HttpServletResponse response,long requestTime) throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Thread.sleep(new Random().nextInt(1000)); //模拟业务处理时间
        int num = new Random().nextInt(100); //模拟查询到的数据，查到就返回
        long responseTime = System.currentTimeMillis();
        if(num >10 && num <50){ //有数据变化
            writer.print(String.format("response time: %s,  request time: %s, result: %d", responseTime,requestTime,num));
        }else{ //无数据变化
            writer.print(String.format("response time: %s,  request time: %s, result: %s", responseTime,requestTime,"无数据变化"));
        }
    }

    /**
     * iframe轮询
     * @param response    HttpServletResponse
     * @param requestTime 开始请求时间
     * @throws Exception
     */
    @RequestMapping(value="/iframe/polling", method= RequestMethod.GET)
    public void iframePolling(HttpServletResponse response,long requestTime) throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Thread.sleep(300); //模拟业务处理时间
        int num = new Random().nextInt(50);  //模拟查询到的数据，查到就返回
        long responseTime = System.currentTimeMillis();
        if(num >10 && num <20){ //有数据变化
            writer.print(String.format("response time: %s, request time: %s,result: %d", responseTime,requestTime,num));
        }else{ //无数据变化
            writer.print(String.format("response time: %s, request time: %s,result: %s", responseTime,requestTime,"无数据变化"));
        }
    }
}
