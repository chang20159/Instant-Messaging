package chang.backend.constant;


import chang.backend.dto.UserDTO;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by chang on 2017/3/27.
 */
public class Constants {

    public static final String UID = "uid";

    public static final String CROSS_ORIGIN = "http://localhost:8000";

    public static Map<Long,UserDTO> USERS = Maps.newHashMap();

    static {
        UserDTO user1 = new UserDTO();
        user1.setId(1992L);
        user1.setName("茉莉蜜茶only");
        user1.setImageUrl("http://chang20159.coding.me/images/photoo.jpg");

        UserDTO user2 = new UserDTO();
        user2.setId(1990L);
        user2.setName("_小狐猴");
        user2.setImageUrl("http://tva1.sinaimg.cn/crop.0.0.180.180.180/730be1e5jw1e8qgp5bmzyj2050050aa8.jpg");

        USERS.put(user1.getId(), user1);
        USERS.put(user2.getId(),user2);
    }
}
