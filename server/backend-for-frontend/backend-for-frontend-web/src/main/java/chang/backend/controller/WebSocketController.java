package chang.backend.controller;

import chang.backend.constant.Constants;
import chang.backend.dto.Response;
import chang.backend.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by chang on 2017/3/27.
 */
@Controller
public class WebSocketController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response doLogin(UserDTO user, HttpServletRequest request,HttpServletResponse response) throws Exception{
        addCookie(response,"uid",user.getId().toString());
        addCookie(response, "name", Constants.USERS.get(user.getId()).getName());

        request.getSession().setAttribute("uid", user.getId());
        request.getSession().setAttribute("name", Constants.USERS.get(user.getId()).getName());
        return Response.success();
    }

    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    @ResponseBody
    public Response getLoginInfo(HttpServletRequest request){
        Cookie cookie = getCookie(request.getCookies(),"uid");
        if(cookie == null){
            return Response.fail("无法获取登录信息");
        }
        Long uid = Long.valueOf(cookie.getValue());
        return Response.success(Constants.USERS.get(uid));
    }

    private Cookie getCookie(Cookie[] cookies,String key) {
        for(Cookie cookie : cookies){
            if("uid".equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }

    private void addCookie(HttpServletResponse response,String key,String value) throws Exception{
        Cookie cookie = new Cookie(key, URLEncoder.encode(value,"utf-8"));
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*6);
        response.addCookie(cookie);
    }

}
