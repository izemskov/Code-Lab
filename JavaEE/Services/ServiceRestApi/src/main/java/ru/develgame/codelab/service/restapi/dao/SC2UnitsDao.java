package ru.develgame.codelab.service.restapi.dao;

import ru.develgame.codelab.service.restapi.dto.SC2UnitDto;
import ru.develgame.codelab.service.restapi.entity.SC2Unit;

import java.util.List;

public interface SC2UnitsDao {
    List<SC2Unit> getSC2Units();

    SC2Unit createSC2Unit(SC2UnitDto dto);
}
