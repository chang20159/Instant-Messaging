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
public class SSEController {
    /**
     * 服务器推送事件SSE
     * @param response
     * @param requestTime
     * @throws Exception
     */
    @RequestMapping(value="/server-sent-events", method= RequestMethod.GET)
//    @ResponseBody
    public void serverSentEventsConnection(HttpServletResponse response,long requestTime) throws Exception{
        //server-sent-events.html:1 EventSource's response has a MIME type ("text/plain") that is not "text/event-stream". Aborting the connection.
        //EventSource's response has a charset ("iso-8859-1") that is not UTF-8. Aborting the connection.
//        response.setHeader("content-type","text/event-stream;charset=utf-8");
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        Random random = new Random();
        int num;
        String responseData;
        long responseTime;
        num = random.nextInt(100);
        if(num > 20 && num < 50){ //有数据变化，返回
            responseTime = System.currentTimeMillis();
            responseData = String.format("response time: %s, request time: %s,result: %d", responseTime, requestTime, num);
            writer.println("data:"+responseData);
            writer.println("event:message");
            writer.println();
//            writer.flush();
//            writer.close();
        }
    }
}
