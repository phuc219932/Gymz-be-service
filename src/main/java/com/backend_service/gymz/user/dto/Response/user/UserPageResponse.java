package com.backend_service.gymz.user.dto.Response.user;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
@RequiredArgsConstructor
public class UserPageResponse extends PageResponseAbstract {
    private List<UserResponse> users;
}
