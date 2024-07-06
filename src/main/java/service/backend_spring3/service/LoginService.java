package service.backend_spring3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.backend_spring3.domain.User;
import service.backend_spring3.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component
@Transactional
public class LoginService {
//    private  final LoginRepository  loginRepository;

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    public Long join(User user){

        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByName(user.getName()).ifPresent(m->{
            try {
                throw new IllegalAccessException("이미 존재하는 회원입니다.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId) { return  userRepository.findById(userId); }

}
