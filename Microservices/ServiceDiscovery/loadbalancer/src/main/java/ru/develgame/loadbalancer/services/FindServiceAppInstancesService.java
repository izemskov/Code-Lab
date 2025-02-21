package ru.develgame.loadbalancer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindServiceAppInstancesService {
    @Autowired
    private DiscoveryClient discoveryClient;

    public List<ServiceInstance> findServiceAppInstances() {
        return discoveryClient.getServices()
                .stream()
                .filter(t -> t.equals("serviceapp"))
                .map(t -> discoveryClient.getInstances(t))
                .flatMap(t -> t.stream())
                .collect(Collectors.toList());
    }
}
