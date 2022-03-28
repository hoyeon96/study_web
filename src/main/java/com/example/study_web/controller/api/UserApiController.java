package com.example.study_web.controller.api;

import com.example.study_web.ifs.CrudInterface;
import com.example.study_web.model.network.Header;
import com.example.study_web.model.network.request.UserApiRequest;
import com.example.study_web.model.network.response.UserApiResponse;
import com.example.study_web.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    private final UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}",request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header read(@PathVariable(name = "id") Long id) {
        log.info("read id : {}",id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping// /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        log.info("delete : {}",id);
        return userApiLogicService.delete(id);
    }
}
