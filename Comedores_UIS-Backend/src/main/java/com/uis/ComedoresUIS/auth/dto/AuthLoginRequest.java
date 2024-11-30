package com.uis.ComedoresUIS.auth.dto;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin

public record AuthLoginRequest(String username,
                               String password,
                               boolean isAdmin)  {
}
