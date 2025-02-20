package ru.develgame.codelab.service.restapi.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.develgame.codelab.service.restapi.dao.SC2UnitsDao;
import ru.develgame.codelab.service.restapi.dto.ErrorResponses;
import ru.develgame.codelab.service.restapi.dto.SC2UnitDto;
import ru.develgame.codelab.service.restapi.entity.SC2Unit;
import ru.develgame.codelab.service.restapi.mapper.SC2UnitMapper;

import java.util.List;

/**
 * @author Ilya Zemskov
 */
@Path("sc2units")
public class SC2UnitController {
    @Inject
    private SC2UnitsDao sc2UnitsDao;

    @Inject
    private SC2UnitMapper sc2UnitMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SC2UnitDto> getSC2Units() {
        List<SC2Unit> sc2Units = sc2UnitsDao.getSC2Units();
        return sc2Units.stream().map(sc2UnitMapper::toDto).toList();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSC2Unit(@Valid SC2UnitDto sc2UnitDto) {
//        if (sc2UnitDto.getName() == null || sc2UnitDto.getName().isBlank()) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity(new ErrorResponses("Name cannot be empty"))
//                    .type(MediaType.APPLICATION_JSON)
//                    .build();
//        }

        SC2Unit sc2Unit = sc2UnitsDao.createSC2Unit(sc2UnitDto);

        return Response.status(Response.Status.CREATED)
                .entity(sc2UnitMapper.toDto(sc2Unit))
                .type(MediaType.APPLICATION_JSON)
                .build();

    }
}
