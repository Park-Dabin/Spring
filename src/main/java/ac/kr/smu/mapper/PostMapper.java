package ac.kr.smu.mapper;
import ac.kr.smu.vo.PostVO;

import java.util.List;

public interface PostMapper {
   public void save(PostVO postVO);
   public void delete(int id);
   public List<PostVO> findAll();
   public PostVO findById(int id);
   public PostVO update(PostVO postVO);
}