package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.common.request.LoginRequest;
import org.usman.api.college.services.LoginServiceImpl;

@Slf4j
@RestController
@RequestMapping("/v1/login")
@Tag( name= "User's Login management", description = "User's Login application")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final LoginServiceImpl loginService;

    @PostMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Validate user login")
    public ResponseEntity<?> validateUser(@RequestBody LoginRequest request) {

        log.info("users info: {}", request);
        return ResponseEntity.ok(loginService.validateUser(request));
    }

}
