package com.example.shoppingApp.api_models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequest {

    private String name;
    private String email;
}
