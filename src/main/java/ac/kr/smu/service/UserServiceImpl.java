package ac.kr.smu.service;

import ac.kr.smu.mapper.UserMapper;
import ac.kr.smu.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;

    @Override
    public void save(UserVO userVO) {
        userMapper.save(userVO);
    }

    @Override
    public boolean checkEmailDuplication(String email) {
        return userMapper.checkEmailDuplication(email)==0;
    }

    @Override
    public boolean checkPassword(String email, String password) {
        return password.equals(userMapper.findByEmail(email).getPassword());
    }

    @Override
    public UserVO findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}
