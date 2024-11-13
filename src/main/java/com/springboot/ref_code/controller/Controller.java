package com.springboot.ref_code.controller;

import com.springboot.ref_code.exception.ValidationCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class Controller {

    private HttpHeaders headers;
    private Map<String, Object> body = new LinkedHashMap<String, Object>() {
        {
            put("code", ValidationCode.SUCCESS.getCode());
            put("message", ValidationCode.SUCCESS.getMessage());
        }
    };

    public Controller() {
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
    }

    @GetMapping("/getController")
    public ResponseEntity getController(@Valid @NotNull String name) throws Exception{
        body.put("data","getController");

        return new ResponseEntity(body,headers, HttpStatus.OK);
    }

    @PostMapping("/postController")
    public ResponseEntity postController(@Valid @RequestBody @NotNull String name) throws Exception{
        body.put("data","postController");

        return new ResponseEntity(body,headers,HttpStatus.OK);
    }
}
