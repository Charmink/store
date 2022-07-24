package com.charm1nk.store.unit.service;

import com.charm1nk.store.dto.CreateProductRequest;
import com.charm1nk.store.exception.MakerNotExistException;
import com.charm1nk.store.exception.PartitionNotExistException;
import com.charm1nk.store.exception.ProductNotExistException;
import com.charm1nk.store.model.Currency;
import com.charm1nk.store.model.Maker;
import com.charm1nk.store.model.Partition;
import com.charm1nk.store.model.Product;
import com.charm1nk.store.repository.MakerRepository;
import com.charm1nk.store.repository.PartitionRepository;
import com.charm1nk.store.repository.ProductRepository;
import com.charm1nk.store.service.impl.ProductServiceImpl;
import com.charm1nk.store.service.search.ProductSearchService;
import com.charm1nk.store.unit.UnitTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static com.charm1nk.store.common.TestUtils.randomString;
import static com.charm1nk.store.common.TestUtils.randomPositiveNumber;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

public class ProductServiceTest extends UnitTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductSearchService productSearchService;

    @Mock
    private PartitionRepository partitionRepository;

    @Mock
    private MakerRepository makerRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    private String makerName;
    private Long productId;

    @BeforeEach
    public void beforeEach() {
        makerName = randomString();
        productId = randomPositiveNumber().longValue();
    }

    @Test
    void shouldSuccessfullyCreateProduct() {
        final var createProductRequest = generateCreateProductRequest();
        final var generatedMaker = generateMaker();
        final var generatedPartition = generatePartition();

        when(makerRepository.findMakerByNameContainsIgnoreCase(any())).thenReturn(Optional.of(generatedMaker));
        when(partitionRepository.findById(any())).thenReturn(Optional.of(generatedPartition));
        when(productRepository.save(productArgumentCaptor.capture())).then(returnsFirstArg());

        productService.createProduct(createProductRequest);

        final var product = productArgumentCaptor.getValue();

        verify(makerRepository, times(1)).findMakerByNameContainsIgnoreCase(makerName);
        verify(partitionRepository, times(1)).findById(createProductRequest.getPartitionId());
        verify(productRepository, times(1)).save(product);

        assertEquals(createProductRequest.getAvailable(), product.getAvailable());
        assertEquals(createProductRequest.getCurrency(), product.getCurrency());
        assertEquals(createProductRequest.getDescription(), product.getDescription());
        assertEquals(createProductRequest.getName(), product.getName());
        assertEquals(createProductRequest.getGuarantee(), product.getGuarantee());
        assertEquals(createProductRequest.getPrice(), product.getPrice());
        assertEquals(createProductRequest.getMakerName(), product.getMaker().getName());
    }

    @Test
    void shouldThrowMakerNotExistExceptionIfMakerNotExist() {
        final var createProductRequest = generateCreateProductRequest();

        when(makerRepository.findMakerByNameContainsIgnoreCase(any())).thenReturn(Optional.empty());

        final var actualException = assertThrows(
                MakerNotExistException.class,
                () -> productService.createProduct(createProductRequest)
        );

        verify(makerRepository, times(1)).findMakerByNameContainsIgnoreCase(makerName);
        verify(partitionRepository, never()).findById(any());
        verify(productRepository, never()).save(any());

        assertEquals(String.format("Maker with name %s not exist", makerName), actualException.getMessage());
    }

    @Test
    void shouldThrowPartitionNotExistExceptionIfPartitionNotExist() {
        final var createProductRequest = generateCreateProductRequest();
        final var generatedMaker = generateMaker();

        when(makerRepository.findMakerByNameContainsIgnoreCase(any())).thenReturn(Optional.of(generatedMaker));
        when(partitionRepository.findById(any())).thenReturn(Optional.empty());

        final var actualException = assertThrows(
                PartitionNotExistException.class,
                () -> productService.createProduct(createProductRequest)
        );

        verify(makerRepository, times(1)).findMakerByNameContainsIgnoreCase(makerName);
        verify(partitionRepository, times(1)).findById(createProductRequest.getPartitionId());
        verify(productRepository, never()).save(any());

        assertEquals(
                String.format("Partition with id %d not exist", createProductRequest.getPartitionId()),
                actualException.getMessage()
        );
    }

    @Test
    void shouldSuccessfullyGetProductById() {
        final var generatedProduct = generateProduct();

        when(productRepository.findById(productId)).thenReturn(Optional.of(generatedProduct));

        final var actualProduct = productService.getProduct(productId);

        verify(productRepository, times(1)).findById(productId);

        assertEquals(generatedProduct.getAvailable(), actualProduct.getAvailable());
        assertEquals(generatedProduct.getCurrency(), actualProduct.getCurrency());
        assertEquals(generatedProduct.getDescription(), actualProduct.getDescription());
        assertEquals(generatedProduct.getGuarantee(), actualProduct.getGuarantee());
        assertEquals(generatedProduct.getPrice(), actualProduct.getPrice());
        assertEquals(generatedProduct.getName(), actualProduct.getName());
        assertEquals(generatedProduct.getMaker().getName(), actualProduct.getMakerName());
        assertEquals(generatedProduct.getPartition().getName(), actualProduct.getPartitionName());
    }

    @Test
    void shouldThrowProductNotExistExceptionIfProductNotExist() {
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        final var actualException = assertThrows(
                ProductNotExistException.class,
                () -> productService.getProduct(productId)
        );

        verify(productRepository, times(1)).findById(productId);

        assertEquals(String.format("Product with id %d not exist", productId), actualException.getMessage());
    }

    private Product generateProduct() {
        final var product = new Product();
        product.setPartition(generatePartition());
        product.setMaker(generateMaker());
        product.setPrice(randomPositiveNumber().doubleValue());
        product.setGuarantee(randomPositiveNumber().longValue());
        product.setDescription(randomString());
        product.setCurrency(Currency.RUR);
        product.setName(randomString());
        product.setAvailable(true);

        return product;
    }

    private Maker generateMaker() {
        final var maker = new Maker();
        maker.setName(makerName);
        maker.setCountry(randomString());

        return maker;
    }

    private Partition generatePartition() {
        final var partition = new Partition();
        partition.setName(randomString());

        return partition;
    }

    private CreateProductRequest generateCreateProductRequest() {
        final var createProductRequest = new CreateProductRequest();
        createProductRequest.setPartitionId(randomPositiveNumber().longValue());
        createProductRequest.setPrice(randomPositiveNumber().doubleValue());
        createProductRequest.setMakerName(makerName);
        createProductRequest.setName(randomString());
        createProductRequest.setGuarantee(randomPositiveNumber().longValue());
        createProductRequest.setDescription(randomString());
        createProductRequest.setCurrency(Currency.RUR);
        createProductRequest.setAvailable(true);

        return createProductRequest;
    }
}
