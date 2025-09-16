package com.backend_service.gymz.fitness.dto.response.contract;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class ContractPageResponse extends PageResponseAbstract {
    private List<ContractResponse> contracts;
}
