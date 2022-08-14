package com.charm1nk.store.service.search;

import com.charm1nk.store.model.Product;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

@Transactional
@Repository
@RequiredArgsConstructor
public class ProductSearchService {

    @PersistenceContext
    private final EntityManager entityManager;

    public FullTextQuery searchProducts(Integer page, Integer size, String query) {

        final var fullTextEntityManager = getFullTextEntityManager(entityManager);

        final var queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Product.class).get();

        final var combinedQuery = queryBuilder
                .bool()
                .should(queryBuilder
                        .keyword()
                        .wildcard()
                        .onFields("name", "description")
                        .matching(String.format("*%s*", query).toLowerCase())
                        .createQuery()
                )
                .should(queryBuilder
                        .phrase()
                        .withSlop(10)
                        .onField("description")
                        .andField("name")
                        .sentence(query)
                        .createQuery())

                .createQuery();

        return fullTextEntityManager
                .createFullTextQuery(combinedQuery, Product.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .setSort(
                        new Sort(
                                new SortField(
                                        "id",
                                        SortField.Type.STRING,
                                        true
                                )
                        )
                );
    }
}
