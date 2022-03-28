package com.example.study_web.service;

import com.example.study_web.ifs.CrudInterface;
import com.example.study_web.model.entity.User;
import com.example.study_web.model.network.Header;
import com.example.study_web.model.network.request.UserApiRequest;
import com.example.study_web.model.network.response.UserApiResponse;
import com.example.study_web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse>{

    private final UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(userApiRequest.getStatus())
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .registeredAt(userApiRequest.getRegisteredAt())
                .build();
        User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id).map(user -> response(user)).orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터 를 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
                    // 3. data -> update
                    // id
                    user.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setStatus(userApiRequest.getStatus())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setEmail(userApiRequest.getEmail())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
                    return user;

                })
                .map(user -> userRepository.save(user))             // update -> newUser
                .map(user -> response(user))                        // userApiResponse
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = userRepository.findById(id);

        return optional.map(user ->{
                    userRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .account(user.getAccount())
                .password(user.getPassword())
                .status(user.getStatus())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .build();
        return Header.OK(userApiResponse);
    }
}