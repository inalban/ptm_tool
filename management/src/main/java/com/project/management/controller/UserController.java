package com.project.management.controller;

import com.project.management.model.User;
import com.project.management.payload.JWTLoginSuccessResponse;
import com.project.management.payload.LoginRequest;
import com.project.management.security.JWTTokenProvider;
import com.project.management.services.MapValidationErrorService;
import com.project.management.services.UserService;
import com.project.management.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

import static com.project.management.security.SecurityConstants.TOKEN_PREFIX;

@Slf4j
@RestController
@RequestMapping("/api/users")


public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private JWTTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        //try
        logger.info("**************Token "+jwt);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        //validate password match
        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;
        User newUser=new User();
        newUser = userService.saveUser(user);
        System.out.println(newUser.toString());
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }


}