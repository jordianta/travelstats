package com.trebol.travelstats.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Slf4j
public class KeepALiveTask {


    private static final int TIME_PING_DB_IN_MILLIS = 60 * 60 * 1000;
    private static final String QUERY = "SELECT 1 FROM DUAL";

    @PersistenceContext
    private final EntityManager entityManager;

    public KeepALiveTask(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Scheduled(fixedRate = TIME_PING_DB_IN_MILLIS)
    public void scheduleTaskToKeepALiveDBConnection() {
        log.error("KeepALive: {}", entityManager.createNativeQuery(QUERY).getSingleResult());
    }

}
