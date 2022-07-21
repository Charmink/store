package com.charm1nk.store.service.search;

import com.charm1nk.store.dto.GetProductsResponse;
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
        //извлекаем fullTextEntityManager, используя entityManager
        final var fullTextEntityManager = getFullTextEntityManager(entityManager);

        // создаем запрос при помощи Hibernate Search query DSL
        final var queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Product.class).get();

        //обозначаем поля, по которым необходимо произвести поиск
        final var query = queryBuilder
                .keyword()
                .onFields("name", "description")
                .matching(text)
                .createQuery();

        //оборачиваем Lucene Query в Hibernate Query object
        final var jpaQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);
        //возвращаем список сущностей
        return jpaQuery.getResultStream().map((result) -> (Product) result).toList();
    }
}
