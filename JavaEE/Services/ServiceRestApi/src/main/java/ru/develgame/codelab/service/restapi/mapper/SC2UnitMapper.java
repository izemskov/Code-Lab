package ru.develgame.codelab.service.restapi.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import ru.develgame.codelab.service.restapi.dto.SC2UnitDto;
import ru.develgame.codelab.service.restapi.entity.SC2Unit;

@ApplicationScoped
public class SC2UnitMapper {
    public SC2UnitDto toDto(SC2Unit dbBean) {
        SC2UnitDto dto = new SC2UnitDto();
        dto.setId(dbBean.getId());
        dto.setName(dbBean.getName());
        dto.setAttack(dbBean.getAttack());
        dto.setDefense(dbBean.getDefense());
        return dto;
    }
}
