package com.JWT.JWT.Authnetication.Controller;

import com.JWT.JWT.Authnetication.Dto.PagebleResponse;
import com.JWT.JWT.Authnetication.Dto.UserDto;
import com.JWT.JWT.Authnetication.Exception.Exceptionhandler;
import com.JWT.JWT.Authnetication.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto ,@PathVariable Long id){
        UserDto updateUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }


    @GetMapping("/allUsers")
     public ResponseEntity<PagebleResponse<UserDto>> getAllUser(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                @RequestParam(value = "pageNumber",defaultValue = "20",required = false) int pageSize,
                                                                @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
                                                                @RequestParam(value = "sortDir",defaultValue = "ASC",required = false) String sortDir){
         PagebleResponse<UserDto> allUser = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
         return new ResponseEntity<>(allUser,HttpStatus.OK);

     }

     @DeleteMapping("/delete/{id}")
     public ResponseEntity<Exceptionhandler> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
         Exceptionhandler deleteUser = Exceptionhandler.builder().localDate(LocalDate.now()).httpStatus(HttpStatus.OK).success(true).message("User is deleted").build();
         return new ResponseEntity<>(deleteUser,HttpStatus.OK);
     }



     //some changes in spring boot application

}
