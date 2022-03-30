package com.example.study_web.service;

import com.example.study_web.ifs.CrudInterface;
import com.example.study_web.model.entity.Item;
import com.example.study_web.model.entity.Partner;
import com.example.study_web.model.entity.User;
import com.example.study_web.model.network.Header;
import com.example.study_web.model.network.request.ItemApiRequest;
import com.example.study_web.model.network.response.ItemApiResponse;
import com.example.study_web.model.network.response.UserApiResponse;
import com.example.study_web.repository.ItemRepository;
import com.example.study_web.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final ItemRepository itemRepository;
    private final PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();

        Long partnerId = itemApiRequest.getPartnerId();
        Optional<Partner> partner = partnerRepository.findById(partnerId);
        partner.ifPresent(selectPartner -> {
            selectPartner.getId();
        });

        Item item = Item.builder()
                .status(itemApiRequest.getStatus())
                .name(itemApiRequest.getName())
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .brandName(itemApiRequest.getBrandName())
                .registeredAt(itemApiRequest.getRegisteredAt())
//                .partner()
                .build();
        Item newItem = itemRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository.findById(id).map(item -> response(item)).orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<ItemApiResponse> response(Item item){
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(itemApiResponse);
    }
}
