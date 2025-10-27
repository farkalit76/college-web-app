package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.common.request.LoginRequest;
import org.usman.api.college.repository.entities.Users;
import org.usman.api.college.services.LoginServiceImpl;
import org.usman.api.college.services.UsersServiceImpl;

import java.util.Optional;

/**
 * UserController (for students)
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@Tag( name= "Users management", description = "Users application")
@RequiredArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersService;

    private final LoginServiceImpl loginService;

    @GetMapping("/fetch/all")
    @Operation(description = "Get all Users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/fetch/{userId}")
    @Operation(description = "Get a User by userId")
    public ResponseEntity<?> getUsersByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(usersService.getUsersById(userId));
    }

    @GetMapping("/fetch/username/{userName}")
    @Operation(description = "Get a User by userId")
    public ResponseEntity<?> searchByUserName(@PathVariable(required = false) Optional<String> userName){
        if (userName.isPresent() && !userName.get().equals("*")) {
            return ResponseEntity.ok(usersService.searchByUserName(userName.get()));
        } else {
            return ResponseEntity.ok(usersService.getAllUsers());
        }
    }

    @PostMapping("/create")
    @Operation(description = "Create a User")
    public ResponseEntity<?> saveUsers(@RequestBody Users user) {
        log.info("Controller saveUsers started..{}", user);
        return ResponseEntity.ok(usersService.createUsers(user));
    }

    @PutMapping("/update/{userId}")
    @Operation(description = "Update a User")
    public ResponseEntity<?> updateUsers(@PathVariable String userId, @RequestBody Users user) {
        return ResponseEntity.ok(usersService.updateUsers(userId, user));
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(description = "Delete a User")
    public ResponseEntity<?> deleteUsers(@PathVariable String userId) {
        return ResponseEntity.ok(usersService.deleteUsers(userId));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Validate user login")
    public ResponseEntity<?> validateUser(@RequestBody LoginRequest request) {
        log.info("users info: {}", request);
        return ResponseEntity.ok(loginService.validateUser(request));
    }
}