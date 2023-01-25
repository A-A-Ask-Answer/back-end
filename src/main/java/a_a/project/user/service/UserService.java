package a_a.project.user.service;

import a_a.project.exception.user.UserErrorCode;
import a_a.project.exception.user.UserException;
import a_a.project.user.dto.UserSaveDto;
import a_a.project.user.entity.User;
import a_a.project.user.entity.type.Gender;
import a_a.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String newUserSave(UserSaveDto.Request request) {
        if (userRepository.existsByKeyWord(request.getKeyword())) {
            throw new UserException(UserErrorCode.DUPLICATE_KEYWORD);
        }
        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new UserException(UserErrorCode.DUPLICATE_LOGIN_ID);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new UserException(UserErrorCode.NOT_EQUALS_PASSWORD);
        }
        User user = request.toEntity(bCryptPasswordEncoder.encode(request.getPassword()));
        if (request.getGender().equals("MALE")) {
            user.gender(Gender.MALE);
        } else {
            user.gender(Gender.FEMALE);
        }

        userRepository.save(user);


        return "회원가입 성공";
    }

    public String duplicateLoginId(String loginId) {
        if (userRepository.existsByLoginId(loginId)) {
            throw new UserException(UserErrorCode.DUPLICATE_LOGIN_ID);
        }
        return "사용 가능한 아이디입니다.";
    }

    public String duplicateKeyword(String keyword) {
        if (userRepository.existsByKeyWord(keyword)) {
            throw new UserException(UserErrorCode.DUPLICATE_KEYWORD);
        }
        return "사용 가능한 키워드입니다.";
    }
}
