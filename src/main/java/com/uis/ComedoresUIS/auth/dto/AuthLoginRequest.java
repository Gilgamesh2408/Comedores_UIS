package com.uis.ComedoresUIS.auth.dto;

public record AuthLoginRequest(String username,
                               String password,
                               boolean isAdmin)  {
}
