package ru.develgame.javaeerestapi.dao;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.UserTransaction;
import ru.develgame.javaeerestapi.entity.SC2Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class SC2UnitsDaoImpl implements SC2UnitsDao {
    @Inject
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private Logger logger;

    @Override
    @SuppressWarnings("unchecked")
    public List<SC2Unit> getSC2Units() {
        Query query = entityManager.createNativeQuery("SELECT ID, NAME, ATTACK, DEFENCE FROM APP.SC2_UNITS",
                SC2Unit.class);
        return new ArrayList<>(query.getResultList());
    }
}
