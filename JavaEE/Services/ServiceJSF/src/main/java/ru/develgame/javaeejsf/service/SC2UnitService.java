package ru.develgame.javaeejsf.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.develgame.javaeejsf.dto.SC2UnitDto;

import java.util.List;

/**
 * @author Ilya Zemskov
 */
@Named
@ApplicationScoped
public class SC2UnitService {
    private Client client;

    private static final String REST_API_ADDRESS = "http://localhost:8080/service-rest-api-1.0.0/";
    private static final String RESOURCES_ADDRESS = "resources/sc2units";

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    public List<SC2UnitDto> getSc2UnitList() {
        List<SC2UnitDto> sc2Units = client
                .target(REST_API_ADDRESS)
                .path(RESOURCES_ADDRESS)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<SC2UnitDto>>() {});
        return sc2Units;
    }

    public Response createSc2Unit(SC2UnitDto sc2Unit) {
        return client
                .target(REST_API_ADDRESS)
                .path(RESOURCES_ADDRESS)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(sc2Unit, MediaType.APPLICATION_JSON));
    }
}
