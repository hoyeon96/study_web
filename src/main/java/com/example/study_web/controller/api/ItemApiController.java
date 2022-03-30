package com.example.study_web.controller.api;

import com.example.study_web.ifs.CrudInterface;
import com.example.study_web.model.network.Header;
import com.example.study_web.model.network.request.ItemApiRequest;
import com.example.study_web.model.network.response.ItemApiResponse;
import com.example.study_web.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final ItemApiLogicService itemApiLogicService;


    @Override
    @PostMapping("") // /api/item
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        log.info("{}",request);
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read id : {}",id);
        return itemApiLogicService.read(id);
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
