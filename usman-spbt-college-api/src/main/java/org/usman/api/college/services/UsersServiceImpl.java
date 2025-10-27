package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.usman.api.college.common.utility.EncryptUtil;
import org.usman.api.college.repository.UsersRepository;
import org.usman.api.college.repository.entities.Users;

import java.util.List;
import java.util.Random;

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
public class UsersServiceImpl {

    private final UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll(Sort.by("updatedAt").descending().and(Sort.by("status").descending()));
    }

    public Users getUsersById(String userId) {
        return usersRepository.findById(Long.parseLong(userId))
               .orElseThrow(() -> new RuntimeException("Users not found for Id: " + userId));
    }

    public List<Users> searchByUserName(String userName) {
        log.info("UserService search by userName Like :{}", userName);
        return usersRepository.findByUserNameLike(userName);
    }

    public Users createUsers(Users users) {
        log.info("Service saveUsers started..");
        if(users.getUserId().equals(0L)){
            users.setUserId(getUserId());
            users.setPassword(EncryptUtil.hashPassword(users.getPassword()));
            log.info("Creating Users with :{}", users);
            return usersRepository.save(users);
        }else {
            log.info("User Id already exists, please update it.");
            return updateUsers(String.valueOf(users.getUserId()), users);
        }
    }

    public Users updateUsers(String userId, Users users) {
        Users existing = usersRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("Users not found for id : " + userId));
        //update only Role and Status
        existing.setRole(users.getRole());
        existing.setStatus(users.getStatus());

        return usersRepository.save(existing);
    }

    public int deleteUsers(String userId) {
        usersRepository.deleteByUserId(Long.parseLong(userId));
        return 1;
    }

    private Long getUserId(){
        Random random = new Random();
        return random.nextInt(99000) + 1000L;//start from 1000
    }
}
