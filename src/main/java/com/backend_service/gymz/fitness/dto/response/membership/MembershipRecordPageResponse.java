package com.backend_service.gymz.fitness.dto.response.membership;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class MembershipRecordPageResponse extends PageResponseAbstract {
    private List<MembershipRecordResponse> membershipRecords;
}
