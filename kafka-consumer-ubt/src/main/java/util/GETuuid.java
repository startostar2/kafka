package util;

import java.util.UUID;

/**
 * Created by x_deng on 2018/3/1.
 */
public class GETuuid {
    public static String getId() {
        String id = UUID.randomUUID().toString();
        id = id.replace("-", "");//替换掉中间的那个斜杠
        return id;
    }
}
