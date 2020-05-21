package com.application.org.Netflix.Application.servicepackages.serivceclasses.service;

import com.application.org.Netflix.Application.servicepackages.Repository.UserRepository;
import com.application.org.Netflix.Application.servicepackages.Util.utility;
import com.application.org.Netflix.Application.servicepackages.serivceclasses.serviceInterface.SerivceInterface;
import com.application.org.Netflix.Application.ui.model.Entity.UserEntity;
import com.application.org.Netflix.Application.ui.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService implements SerivceInterface {

    @Autowired
    utility util;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = new UserEntity();

        if(userRepository.findByEmail(userDetails.getEmail())!= null) throw new RuntimeException("User with email already exists");
        userDetails.setUserId(util.generateUserId(20));
        userEntity = modelMapper.map(userDetails,UserEntity.class);
        UserEntity savedData=userRepository.save(userEntity);

        UserDto returnData=modelMapper.map(savedData,UserDto.class);



        return returnData;
    }

    @Override
    public UserDto getUserById(String userId) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = new UserEntity();

        userEntity = userRepository.findByUserId(userId);

        UserDto userDto = modelMapper.map(userEntity,UserDto.class);

        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        ModelMapper modelMapper = new ModelMapper();
        if(userRepository.findByUserId(userId) == null) throw new RuntimeException("No User Found");
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity updatedData= userRepository.save(userEntity);
        UserDto returnedValue= modelMapper.map(updatedData,UserDto.class);

        return returnedValue;
    }

    @Override
    public String deleteUser(String userId) {
        if(userRepository.findByUserId(userId)==null) throw new RuntimeException("No User Found");
        userRepository.deleteByUserId(userId);
        return "Deleted";
    }
}
