package com.subproject.user_and_messages.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.subproject.user_and_messages.dtos.response.UserSearchResponse;
import com.subproject.user_and_messages.dtos.user.UserCreateDTO;
import com.subproject.user_and_messages.dtos.user.UserFormPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserUpdateDTO;
import com.subproject.user_and_messages.search.filter.UserSearchCriteria;

public interface UserService {


    public Page<UserSearchResponse> searchUsers(UserSearchCriteria userCriteria, Pageable pageable);

    public UserPreviewDTO findById(Long id);

    public UserPreviewDTO getUserByNickname(String nickname);

    public Long getUserIdByNickname(String nickname);

    public void deleteUserById(Long id);

    public UserPreviewDTO updateUser(UserUpdateDTO userDto, Long id);

    public UserPreviewDTO createUser(UserCreateDTO userCreateDTO);

    public UserFormPreviewDTO getUserFormDTOByNickname(String nickname);


    
}
