package ac.kr.smu.mapper;

import ac.kr.smu.vo.UserVO;

public interface UserMapper {
    public void save(UserVO userVO);
    public int checkEmailDuplication(String email);
    public UserVO findByEmail(String email);
}
