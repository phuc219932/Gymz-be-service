package com.backend_service.gymz.fitness.dto.response.equipment;

import lombok.*;
import java.util.List;

import com.backend_service.gymz.common.model.PageResponseAbstract;

@Getter
@Setter
public class EquipmentPageReponse extends PageResponseAbstract {
    private List<EquipmentResponse> equipments;
}
