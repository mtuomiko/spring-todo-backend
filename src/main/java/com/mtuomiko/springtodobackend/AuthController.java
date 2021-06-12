package com.mtuomiko.springtodobackend;

import com.mtuomiko.springtodobackend.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            Account account = accountRepository.findByUsername(userDetails.getUsername());

            String token = jwtUtils.generateToken(account);
            String result = String.format("{\"token\":\"%s\"}", token);
            return ResponseEntity.ok().body(result);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public Account register(@RequestBody @Valid RegisterRequest request) {
        return customUserDetailsService.create(request);
    }
}
