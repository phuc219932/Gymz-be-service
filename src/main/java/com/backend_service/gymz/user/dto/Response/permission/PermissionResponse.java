package com.backend_service.gymz.user.dto.Response.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private Integer id;
    private String resource;  // Ví dụ: USER, ORDER, PRODUCT
    private String action;    // Ví dụ: READ, CREATE, UPDATE, DELETE
    private String scope;     // Ví dụ: OWN, ALL
}
