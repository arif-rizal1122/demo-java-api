package com.demo_api.demo_api.helpers;

import com.demo_api.demo_api.dto.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationHelper {

    public static <T> ResponseEntity<ResponseData<T>> validateRequest(BindingResult result) {
        ResponseData<T> responseData = new ResponseData<>();

        if (result.hasErrors()) {
            for (FieldError eResult : result.getFieldErrors()) {
                responseData.getMessages().add(eResult.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        return null; 
    }
}