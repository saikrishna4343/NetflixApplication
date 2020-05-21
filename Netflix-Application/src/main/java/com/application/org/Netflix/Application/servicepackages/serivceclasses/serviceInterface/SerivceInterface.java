package com.application.org.Netflix.Application.servicepackages.serivceclasses.serviceInterface;


import com.application.org.Netflix.Application.ui.model.dto.UserDto;

public interface SerivceInterface {

     UserDto createUser(UserDto userDetails);

     UserDto getUserById(String userId);

    UserDto updateUser(UserDto userDto, String userId);

    String deleteUser(String userId);
}
