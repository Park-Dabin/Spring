package ac.kr.smu.service;

import ac.kr.smu.vo.PostVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    public void save(PostVO postVO);
    public void delete(int id);
    public List<PostVO> findAll();
    public PostVO findById(int id);
    public PostVO update(PostVO postVO);

}
