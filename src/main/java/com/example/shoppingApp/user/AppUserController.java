package com.example.shoppingApp.user;

import com.example.shoppingApp.api_models.request.UserRequest;
import com.example.shoppingApp.api_models.response.UserResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<AppUser> users = appUserService.getUsers();
        List<UserResponse> response = new ArrayList<UserResponse>();

        users.forEach(user -> {
            response.add(modelMapper.map(user, UserResponse.class));
        });
        return new ResponseEntity<List<UserResponse>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") Long id) {
        UserResponse response = modelMapper.map(appUserService.getUser(id), UserResponse.class);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }

    @PostMapping(path = "new")
    public ResponseEntity<UserResponse> addUser (@RequestBody UserRequest request) {
        AppUser user = appUserService.addUser(new AppUser(request.getName(), request.getEmail()));
        UserResponse response = new UserResponse(user.getId(), user.getName(), user.getEmail());
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }
}
