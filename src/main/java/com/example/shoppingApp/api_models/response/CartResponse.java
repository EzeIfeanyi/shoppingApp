package com.example.shoppingApp.api_models.response;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Data
public class CartResponse {
    private Long id;
    private String userEmail;
}
