package chang.backend.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by chang on 2017/3/27.
 */
@Data
public class MessageDTO {

    /**
     * 发送者
     */
    private UserDTO from;

    /**
     * 接受者
     */
    private UserDTO to;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date sendTime;

}
