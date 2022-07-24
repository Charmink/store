package com.charm1nk.store.service.search;

import com.charm1nk.store.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

@Transactional
@Repository
@RequiredArgsConstructor
public class ProductSearchService {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Product> searchProducts(String text) {

        final var fullTextEntityManager = getFullTextEntityManager(entityManager);

        final var queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Product.class).get();

        final var query = queryBuilder
                .keyword()
                .onFields("name", "description")
                .matching(text)
                .createQuery();

        final var jpaQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);

        return (List<Product>) jpaQuery.getResultList();
    }
}
