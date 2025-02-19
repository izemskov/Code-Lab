package ru.develgame.javaeerestapi.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ru.develgame.javaeerestapi.dao.SC2UnitsDao;
import ru.develgame.javaeerestapi.dto.SC2UnitDto;
import ru.develgame.javaeerestapi.entity.SC2Unit;
import ru.develgame.javaeerestapi.mapper.SC2UnitMapper;

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

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createSC2Unit(SC2Unit sc2Unit) {
//        StringBuilder err = new StringBuilder();
//        boolean created = sc2UnitService.createSC2Unit(sc2Unit, err);
//        if (created)
//            return Response.status(Response.Status.CREATED).build();
//        else
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity(err.toString())
//                    .type(MediaType.TEXT_PLAIN)
//                    .build();
//    }
}
