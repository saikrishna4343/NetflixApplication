package com.application.org.Netflix.Application.ui.controller;

import com.application.org.Netflix.Application.ui.model.request.UserRequest;
import com.application.org.Netflix.Application.ui.model.response.UserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@RestController
@RequestMapping("/users")// http://localhost:8080/users
public class UserController {


    @GetMapping(path="/getUser/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId){

        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName("saikrishna");
        userResponse.setLastName("pidikiti");
        userResponse.setEmail("saikrishnapvn@gmail.com");
        userResponse.setUserId(userId);

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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(userRequest.getFirstName());
        userResponse.setLastName(userRequest.getLastName());
        userResponse.setEmail(userRequest.getEmail());


        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/updateUser",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public String updateUser(){
        return "User Updated";
    }

    @DeleteMapping(path="/deleteUser",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public String deleteUser(){
        return "User Deleted";
    }
}
