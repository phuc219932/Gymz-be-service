package com.backend_service.gymz.user.dto.Response.permission;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class PermissionPageReponse extends PageResponseAbstract {
    private List<PermissionResponse> permissions;
  
}
