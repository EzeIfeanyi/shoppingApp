package com.example.shoppingApp.api_models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse<T> {
    String message = "process successful";
    Boolean isSuccess = true;
    List<String> error = new ArrayList<>();
    T data;
}
