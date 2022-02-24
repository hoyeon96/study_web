package com.example.study_web.controller;

import com.example.study_web.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    // localhost:8080/api/getRequest
    @RequestMapping(method = RequestMethod.GET, path = "/getRequest")
    public String getRequest() {
        return "hello java!";
    }

    @GetMapping("/getParameter")    // http://localhost:8080/api/getParameter?id=1234&&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        System.out.println("id :" + id);
        System.out.println("password :" + pwd);
        return id+pwd;
    }

    @GetMapping("/getMultiParameter") // http://localhost:8080/api/getMultiParameter?account=1234&&email=abcd@abcd&&page=7
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        System.out.println(searchParam.toString());
        searchParam.setPage(1); //
        return searchParam;
    }
}
