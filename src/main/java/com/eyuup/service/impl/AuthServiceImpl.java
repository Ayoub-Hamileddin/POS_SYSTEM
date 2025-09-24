package com.eyuup.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyuup.configuration.JwtProvider;
import com.eyuup.domain.UserRole;
import com.eyuup.exceptions.UserException;
import com.eyuup.mapper.UserMapper;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.UserDto;
import com.eyuup.payload.response.AuthResponse;
import com.eyuup.repository.UserRepository;
import com.eyuup.service.AuthService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;


    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {

         User user=userRepository.findByEmail(userDto.getEmail());
         if (user!=null) {
            throw new UserException("email is already register");
         }
         if (userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserException("role admin is not allowed"); 
         }

         User newUser=new User();
         newUser.setFullName(userDto.getFullName());
         newUser.setEmail(userDto.getEmail());
         newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
         newUser.setRole(userDto.getRole());
         newUser.setPhone(userDto.getPhone());
         newUser.setLastLoginAt(LocalDateTime.now());
         newUser.setCreatedAt(LocalDateTime.now());

         User savedUser=userRepository.save(newUser);


         Authentication authentication=
                                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
         
         SecurityContextHolder.getContext().setAuthentication(authentication);     
         
             String jwt=jwtProvider.generateToken(authentication);

             AuthResponse authResponse=new AuthResponse();
             authResponse.setJwt(jwt);
             authResponse.setMessage("Registred successfuly");
             authResponse.setUser(UserMapper.ToDTO(savedUser));
        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {
      String email=userDto.getEmail();
      String password=userDto.getPassword();

      Authentication authentication = authenticate(email, password);

      SecurityContextHolder.getContext().setAuthentication(authentication);

      Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
      String role =authorities.iterator().next().getAuthority();

      User user=userRepository.findByEmail(email);
      user.setLastLoginAt(LocalDateTime.now());
      userRepository.save(user);

      String jwt = jwtProvider.generateToken(authentication);

      AuthResponse authResponse=new AuthResponse();
      authResponse.setJwt(jwt);
      authResponse.setMessage("login successfuly");
      authResponse.setUser(UserMapper.ToDTO(user));



       return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {
      UserDetails userDetails=customUserImplementation.loadUserByUsername(email);
      if (userDetails==null) {
         throw new UserException("user does not found");
      }
      if (!passwordEncoder.matches(password,userDetails.getPassword() )) {
         
         throw new UserException("password doesn't match ");

      }
      return new UsernamePasswordAuthenticationToken(userDetails,null , userDetails.getAuthorities());
      
    }

}
