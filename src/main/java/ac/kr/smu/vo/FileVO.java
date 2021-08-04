package ac.kr.smu.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileVO {
    public int id;
    public String name;
    public String uuid;
    public String uploadPath;
    public int postId;

    public String getPath(){
        return uploadPath + "/" + uuid + "_" + name;
    }
}
