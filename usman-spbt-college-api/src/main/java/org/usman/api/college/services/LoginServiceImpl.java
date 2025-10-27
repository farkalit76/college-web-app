package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.common.request.LoginRequest;
import org.usman.api.college.common.response.LoginResponse;
import org.usman.api.college.common.utility.EncryptUtil;
import org.usman.api.college.repository.UsersRepository;
import org.usman.api.college.repository.entities.Users;

import java.sql.Timestamp;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @ date 10-07-2025
 * @since 1.0
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl {

    private final UsersRepository usersRepository;

    public LoginResponse validateUser(LoginRequest request) {
        String status = "failed";
        boolean checkPassword = false;
        log.info("UserService search by userName :{}", request.getUsername());
        Users user = usersRepository.findByUserName(request.getUsername());
        log.info("UserService response :{}", user);
        if(null != user) {
            checkPassword = EncryptUtil.checkPassword(request.getPassword(), user.getPassword());
            log.info("UserService checkPassword :{}", checkPassword);
        }
        if (checkPassword) {
            user.setLastLoginAt(new Timestamp(System.currentTimeMillis()));
            usersRepository.save(user);
            status="success";
        }
        return new LoginResponse(request.getUsername(), status);
    }

}
