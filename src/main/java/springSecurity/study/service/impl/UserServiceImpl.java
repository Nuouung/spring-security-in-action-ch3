package springSecurity.study.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springSecurity.study.domain.Account;
import springSecurity.study.repository.UserRepository;
import springSecurity.study.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
