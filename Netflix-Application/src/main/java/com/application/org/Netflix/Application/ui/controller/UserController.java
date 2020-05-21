package com.application.org.Netflix.Application.ui.controller;

import com.application.org.Netflix.Application.servicepackages.Repository.UserRepository;
import com.application.org.Netflix.Application.servicepackages.serivceclasses.service.UserService;
import com.application.org.Netflix.Application.ui.model.dto.UserDto;
import com.application.org.Netflix.Application.ui.model.request.UserRequest;
import com.application.org.Netflix.Application.ui.model.response.UserResponse;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/users")// http://localhost:8080/users
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping(path="/getUser/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId){

        UserResponse userResponse = new UserResponse();
        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto=userService.getUserById(userId);
        userResponse=modelMapper.map(userDto,UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @GetMapping(path="/getUsers", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public String getUser(@RequestParam(value = "page",defaultValue = "1") int page,
                          @RequestParam(value = "limit",defaultValue = "25") int limit,
                          @RequestParam(value = "sort", defaultValue = "asec", required = false) String sort){

        return "Users with page "+page+" limit"+limit+" sorting "+sort;
    }

    @PostMapping(path="/createUser",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        ModelMapper modelMapper = new ModelMapper();
        UserResponse userResponse = new UserResponse();
        UserDto userDto = modelMapper.map(userRequest,UserDto.class);

        UserDto returnValue=userService.createUser(userDto);
        userResponse=modelMapper.map(returnValue,UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/updateUser/{userId}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse>  updateUser(@PathVariable("userId") String userId, @RequestBody UserRequest userRequest){
        UserResponse userResponse = new UserResponse();
        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userRequest,UserDto.class);
        UserDto recievedData=userService.updateUser(userDto,userId);
        userResponse=modelMapper.map(recievedData,UserResponse.class);
        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/deleteUser/{userId}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public String deleteUser(@PathVariable("userId") String userId){
        String deleted=userService.deleteUser(userId);
        return deleted;

    }
}
