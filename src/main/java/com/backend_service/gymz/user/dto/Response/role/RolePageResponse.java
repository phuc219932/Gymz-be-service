package com.backend_service.gymz.user.dto.Response.role;

import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePageResponse  extends PageResponseAbstract{
    List<RoleResponse> roles;
    
}
