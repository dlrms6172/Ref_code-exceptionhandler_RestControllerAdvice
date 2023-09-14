package com.leeroot.springboot.exception.handler.controller;

import com.leeroot.springboot.exception.handler.dto.Dto;
import com.leeroot.springboot.exception.handler.exception.ValidationCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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
    public ResponseEntity getController(@Valid Dto.getController dto) throws Exception{
        body.put("data",dto.getName());

        return new ResponseEntity(body,headers, HttpStatus.OK);
    }

    @PostMapping("/postController")
    public ResponseEntity postController(@Valid @RequestBody Dto.postController dto) throws Exception{
        body.put("data",dto.getName());

        return new ResponseEntity(body,headers,HttpStatus.OK);
    }
}
