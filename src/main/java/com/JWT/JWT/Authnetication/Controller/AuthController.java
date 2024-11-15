package com.JWT.JWT.Authnetication.Controller;

import Dto.JwtRequest;
import Dto.JwtResponse;
import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.User;
import com.JWT.JWT.Authnetication.Security.JwtHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtHelper helper;

    Logger logger  = LoggerFactory.getLogger(AuthController.class);



    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        logger.info("Username {} , Password {} ",jwtRequest.getEmail(),jwtRequest.getPassword());

        this.doAuthenticate(jwtRequest.getEmail(),jwtRequest.getPassword());

        User user = (User)userDetailsService.loadUserByUsername(jwtRequest.getEmail());


        String token = helper.generateToken(user);




        JwtResponse build = JwtResponse.builder().token(token).user(modelMapper.map(user, UserDto.class)).build();

        return ResponseEntity.ok(build);
    }

    private void doAuthenticate(String email, String password) {
        try{
            Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        }catch (BadCredentialsException ex){
            ex.getMessage();
        }
    }
}
