package chang.backend.dto;

import lombok.Data;

/**
 * Created by chang on 2017/3/27.
 */
@Data
public class UserDTO {

    private Long id;

    private String name;

    private String password;

    private String imageUrl;
}
