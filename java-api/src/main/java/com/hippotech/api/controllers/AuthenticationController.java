package com.hippotech.api.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.hippotech.api.data.HippoUserRepository;
import com.hippotech.api.dto.AuthenticationDto;
import com.hippotech.api.dto.Login;
import com.hippotech.api.model.HippoUser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Api(tags = "Authentication")
@RestController
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private HippoUserRepository userRepository;

    @PostMapping(value = "api/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<?> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        HippoUser user = userRepository.findFirstByEmail(authenticationDto.getUsername());
        if (user == null) {
            log.warn("Username not found: " + authenticationDto.getUsername());
        }
        if (user.getPassword().equals(authenticationDto.getPassword())) {
            String token = getJWTToken(authenticationDto.getUsername());
            Login login = new Login();
            login.setUser(authenticationDto.getUsername());
            login.setToken(token);
            return new ResponseEntity<>(login, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}