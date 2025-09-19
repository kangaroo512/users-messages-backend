package com.subproject.user_and_messages.controllers;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subproject.user_and_messages.dtos.request.search.UserSearchRequest;
import com.subproject.user_and_messages.dtos.response.UserSearchResponse;
import com.subproject.user_and_messages.dtos.user.UserCreateDTO;
import com.subproject.user_and_messages.dtos.user.UserFormPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserUpdateDTO;
import com.subproject.user_and_messages.mappers.user.UserSearchMapper;
import com.subproject.user_and_messages.search.filter.UserSearchCriteria;
import com.subproject.user_and_messages.services.UserService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    private UserService userService;
    private UserSearchMapper userSearchMapper;

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

    

    public UserController(
        UserService userService,
        UserSearchMapper userSearchMapper) {
        this.userService = userService;
        this.userSearchMapper = userSearchMapper;
    }

    //create user
    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreate, BindingResult result) {

        System.out.println(userCreate.toString());
        
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        UserPreviewDTO user = userService.createUser(userCreate);

        return ResponseEntity.ok().body(user);


    }

    //update user
    @PutMapping()
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdate, BindingResult result) {

        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for(FieldError err : result.getFieldErrors()) {
                errors.put(err.getField(), err.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Long id = this.userService.getUserIdByNickname(userUpdate.getNickname());
    
        UserPreviewDTO userReturned = this.userService.updateUser(userUpdate, id);



        return ResponseEntity.ok().body(userReturned);
    }

    //delete user

    //Search users functionality
    @PostMapping("search-user/{page}")
    public ResponseEntity<?> searchUsers(@RequestBody UserSearchRequest request, @PathVariable(name="page") Integer page) {
        

        this.logger.info("🚨🚨🚨 [IMPORTANT]" + request.toString());
        //build a DTO 
        UserSearchCriteria criteria = userSearchMapper.requestToCriteria(request);
        //create pageable
        Pageable pageable  = PageRequest.of(page, 10);

        Page<UserSearchResponse> users = this.userService.searchUsers(criteria, pageable);

        return ResponseEntity.ok().body(users);
    }


    //User Preview
    @GetMapping("profile/{nickname}")
    public ResponseEntity<?> getUserByNickname(@PathVariable("nickname") String nickname) {
        return ResponseEntity
        .status(HttpStatus.OK).body(this.userService.getUserByNickname(nickname));

    }

    //Get UserDTO to fill a form for update
    @GetMapping("edit-profile-view/{nickname}")
    public ResponseEntity<?> getUserFormPreview(@PathVariable("nickname") String nickname) {

        //get UserDTO by nickname
        UserFormPreviewDTO userFormPreviewDTO = userService.getUserFormDTOByNickname(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(userFormPreviewDTO);
    }
    
    



}
