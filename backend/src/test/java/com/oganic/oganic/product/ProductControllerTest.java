package com.oganic.oganic.product;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    void initData() {
        product = Product.builder().name("Apple").price(19.9).build();
    }

    @Test
    void getAllProduct_success() throws Exception {
        Mockito.when(productService.getProducts()).thenReturn(Arrays.asList(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(product.getName()));
    }

    @Test
    void getProductPagination_success() throws Exception {
      Pageable pageable = PageRequest.of(0, 3);
        Page<Product> productPage = new PageImpl<>(List.of(product), pageable, 1);

        Mockito.when(productService.getProductsPageable(0, 3)).thenReturn(productPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/list-products")
                .param("page", "1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Apple"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].price").value(19.9));
    }
}
