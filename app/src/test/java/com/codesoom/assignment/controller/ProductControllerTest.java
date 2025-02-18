package com.codesoom.assignment.controller;

import com.codesoom.assignment.application.ProductService;
import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.exception.ToyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ProductController Test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    private final Long ID = 1L;
    private final Long INVALID_ID = 100L;

    @BeforeEach
    void setUp() {


    }

    @Nested
    @DisplayName("GET /products")
    class list {

        @Nested
        @DisplayName("고양이 장난감 목록을 조회하면")
        class when_list {

            @Test
            @DisplayName("200 OK를 응답합니다..")
            void httpStatus_200() throws Exception {
                mockMvc.perform(get("/products"))
                        .andExpect(status().isOk());
            }
        }
    }

    @Nested
    @DisplayName("GET /products/{id}")
    class detail {

        @Nested
        @DisplayName("존재하는 id를 조회하면")
        class when_list_with_valid_id {
            
            @Test
            @DisplayName("200 OK를 응답합니다.")
            void httpStatus_200() throws Exception {
                mockMvc.perform(get("/products/" + ID))
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 id를 조회하면")
        class when_list_with_invalid_id {

            @Test
            @DisplayName("404 NOT FOUND를 응답합니다.")
            void httpStatus_404() throws Exception {
                mockMvc.perform(get("/products/" + INVALID_ID))
                        .andExpect(status().isNotFound());
            }
        }
    }

    @Nested
    @DisplayName("POST /products")
    class register {

        @Nested
        @DisplayName("고양이 장난감을 등록하면")
        class when_register {

            @Test
            @DisplayName("201 CREATED를 응답합니다.")
            void httpStatus_201() throws Exception {
                mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\" : \"고양이 생선\", \"price\" : 10000, \"maker\" : \"(주)애옹이네\", \"imagePath\" : \"image.png\"}")
                        ).andExpect(status().isCreated());
            }
        }
    }

    @Nested
    @DisplayName("PATCH /products/{id}")
    class modify {

        @Nested
        @DisplayName("존재하는 id 라면")
        class when_modify_with_valid_id {

            @Test
            @DisplayName("200 CREATED를 응답합니다.")
            void httpStatus_200() throws Exception {
                mockMvc.perform(
                        patch("/products/" + ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\" : \"생선\", \"price\" : 15000, \"maker\" : \"(주)애옹이네\", \"imagePath\" : \"image.png\"}")
                ).andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 id 라면")
        class when_modify_with_invalid_id {

            @Test
            @DisplayName("404 NOT FOUND를 응답합니다.")
            void httpStatus_200() throws Exception {
                mockMvc.perform(
                        patch("/products/" + INVALID_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\" : \"생선\", \"price\" : 15000, \"maker\" : \"(주)애옹이네\", \"imagePath\" : \"image.png\"}")
                ).andExpect(status().isNotFound());
            }
        }
    }

    @Nested
    @DisplayName("DELETE /products/{id}")
    class delete {

        @Nested
        @DisplayName("존재하는 id라면")
        class when_delete_with_valid_id {

            @Test
            @DisplayName("204 NO CONTENT를 응답합니다.")
            void httpStatus_204() throws Exception {
                mockMvc.perform(delete("/products/" + ID))
                        .andExpect(status().isNoContent());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 id라면")
        class when_delete_with_invalid_id {

            @Test
            @DisplayName("404 NOT FOUND를 응답합니다.")
            void httpStatus_204() throws Exception {
                mockMvc.perform(delete("/products/" + INVALID_ID))
                        .andExpect(status().isNotFound());
            }
        }
    }

}