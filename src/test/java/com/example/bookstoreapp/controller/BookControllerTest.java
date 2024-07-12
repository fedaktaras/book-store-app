package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.BookUpdateDto;
import com.example.bookstoreapp.utils.TestDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    private static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataSource dataSource;

    private TestDataUtils testDataUtils;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @BeforeEach
    public void beforeEach() {
        testDataUtils = new TestDataUtils();
    }

    @Test
    @DisplayName("Create new book")
    @Sql(scripts = {
            "classpath:clear-categories-books.sql",
            "classpath:database/category/add-category-educational-books.sql"
    })
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createBook() throws Exception {
        BookRequestDto bookRequestDto = testDataUtils.getRequestDto("Clean Code");
        BookDto bookDto = testDataUtils.getDto("Clean Code");
        String jsonRequest = objectMapper.writeValueAsString(bookRequestDto);
        MvcResult mvcResult = mockMvc.perform(post("/books")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        BookDto actual = objectMapper.readValue(response.getContentAsString(), BookDto.class);
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(actual, bookDto);
    }

    @Test
    @DisplayName("Get book by Id")
    @WithMockUser
    @Sql(scripts = {"classpath:clear-categories-books.sql",
            "classpath:database/book/create-book-with-id-1.sql"})
    void getBookById() throws Exception {
        BookDto effectiveJavaDto = testDataUtils.getDto("Effective Java");
        MvcResult mvcResult = mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        BookDto actual = objectMapper.readValue(response.getContentAsString(), BookDto.class);
        assertNotNull(actual);
        assertEquals(effectiveJavaDto, actual);
    }

    @Test
    @DisplayName("Get all books")
    @WithMockUser(username = "user")
    @Sql(scripts = {"classpath:clear-categories-books.sql",
            "classpath:database/book/create-three-books.sql"})
    void getAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        BookDto[] bookDtos = objectMapper.readValue(response.getContentAsString(), BookDto[].class);
        Assertions.assertEquals(3, bookDtos.length);
    }

    @Test
    @DisplayName("Update book")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Sql(scripts = {"classpath:clear-categories-books.sql",
            "classpath:database/book/create-book-with-id-1.sql"})
    void updateBook() throws Exception {
        BookUpdateDto bookUpdateDto = new BookUpdateDto();
        String newTitle = "New title";
        bookUpdateDto.setTitle(newTitle);
        bookUpdateDto.setCategoryIds(Set.of(1L));
        String jsonRequest = objectMapper.writeValueAsString(bookUpdateDto);
        MvcResult mvcResult = mockMvc.perform(patch("/books/1")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        BookDto actual = objectMapper.readValue(response.getContentAsString(), BookDto.class);
        Assertions.assertEquals(newTitle, actual.getTitle());
    }

    @Test
    @DisplayName("Delete book")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Sql(scripts = {"classpath:clear-categories-books.sql",
            "classpath:database/book/create-book-with-id-1.sql"})
    void deleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent())
                .andReturn();
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }
}
