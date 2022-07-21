package com.charm1nk.store.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class BuildSearchService implements ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            final var fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}