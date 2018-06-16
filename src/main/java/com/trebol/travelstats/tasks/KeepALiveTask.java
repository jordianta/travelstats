package com.trebol.travelstats.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class KeepALiveTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeepALiveTask.class);

    private static final int TIME_PING_DB_IN_MILLIS = 60 * 60 * 1000;
    private static final String QUERY = "SELECT 1 FROM DUAL";

    @PersistenceContext
    private EntityManager entityManager;

    public KeepALiveTask(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Scheduled(fixedRate = TIME_PING_DB_IN_MILLIS)
    public void scheduleTaskToKeepALiveDBConnection() {
        LOGGER.error("KeepALive: {}", entityManager.createNativeQuery(QUERY).getSingleResult());
    }

}
