package com.example.study_web.controller;

import com.example.study_web.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {
    // @RequestMapping(method = RequestMethod.POST, path = "/postMethod")

    // http://localhost:8080/api/postMethod
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        searchParam.setEmail("admin@admin.co.kr");
        return searchParam;
    }

    // http://localhost:8080/api/putMethod
    @PutMapping(value = "/putMethod")
    public void put(){

    }

    // http://localhost:8080/api/patchMethod
    @PatchMapping(value = "/patchMethod")
    public void patch(){

    }
}
