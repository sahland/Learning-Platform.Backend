package com.knitwit.api.v1.request;

import lombok.Data;

@Data
public class UserRequest {
    private String login;
    private String email;
    private String password;
}