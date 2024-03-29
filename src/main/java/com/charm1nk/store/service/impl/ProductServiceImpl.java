package com.charm1nk.store.service.impl;

import com.charm1nk.store.dto.CreateProductRequest;
import com.charm1nk.store.dto.CreateProductResponse;
import com.charm1nk.store.dto.GetProductResponse;
import com.charm1nk.store.dto.GetProductsResponse;
import com.charm1nk.store.exception.MakerNotExistException;
import com.charm1nk.store.exception.PartitionNotExistException;
import com.charm1nk.store.exception.ProductNotExistException;
import com.charm1nk.store.model.Product;
import com.charm1nk.store.repository.MakerRepository;
import com.charm1nk.store.repository.PartitionRepository;
import com.charm1nk.store.repository.ProductRepository;
import com.charm1nk.store.service.ProductService;

import com.charm1nk.store.service.search.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final PartitionRepository partitionRepository;
    private final MakerRepository makerRepository;
    private final ProductRepository productRepository;
    private final ProductSearchService productSearchService;

    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        final var makerName = createProductRequest.getMakerName();
        final var partitionId = createProductRequest.getPartitionId();

        final var maker = makerRepository.findMakerByNameContainsIgnoreCase(makerName)
                .orElseThrow(
                        () -> new MakerNotExistException(String.format("Maker with name %s not exist", makerName))
                );

        final var partition = partitionRepository.findById(partitionId)
                .orElseThrow(
                        () -> new PartitionNotExistException(
                                String.format("Partition with id %d not exist", partitionId))
                );

        var product = new Product();

        product.setAvailable(createProductRequest.getAvailable());
        product.setCurrency(createProductRequest.getCurrency());
        product.setDescription(createProductRequest.getDescription());
        product.setGuarantee(createProductRequest.getGuarantee());
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setMaker(maker);
        product.setPartition(partition);

        productRepository.save(product);

        return new CreateProductResponse(product.getId());
    }

    public GetProductResponse getProduct(Long productId) {
        final var product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotExistException(
                                String.format("Product with id %d not exist", productId)
                        )
                );

        return GetProductResponse.from(product);
    }

    public GetProductsResponse getProducts(Integer page, Integer size, String query) {
        final var fullTextQuery = productSearchService.searchProducts(page, size, query);
        return GetProductsResponse.from(fullTextQuery.getResultList(), (long) fullTextQuery.getResultSize());
    }
}
