package com.librarysystem.library.controllers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.librarysystem.library.models.Book;
import com.librarysystem.library.repositories.BookRepository;
import com.librarysystem.library.services.BookService;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper ;

    @MockBean
    private BookRepository bookRepository;

    Book RECORD_1=new Book("Atomic Habits","James Clear","2002","9780136019701");
    Book RECORD_2=new Book("Funny Moments","Abdelrahman Mohamed","2002","9780136099701");
    Book RECORD_3=new Book("Competitive Programming 3","Felix Halim","2002","9782136099701");

    @Test
    public void createBook_success() throws Exception {
        Book record =new Book("The last of us","abderahman","2024","9780146019701");

        Mockito.when(bookRepository.save(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.title", is("The last of us")));
    }

    @Test
    public void getAllBooks_success()throws Exception{
        List<Book> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[2].authorName", is("Abdelrahman Mohamed"))
        );
    }
    @Test
    public void getBookById_success() throws Exception {
        Mockito.when(bookRepository.findById(RECORD_1.getId())).thenReturn(java.util.Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.title", is("Atomic Habits")));
    }



    @Test
    public void updateBook_success() throws Exception {
        Book updatedRecord = new Book("The last of us","abderahman","2024","9780146019701");

        Mockito.when(bookRepository.findById(RECORD_1.getId())).thenReturn(Optional.of(RECORD_1));
        Mockito.when(bookRepository.save(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.title", is("The last of us")));
    }

    @Test
    public void deleteBookById_success() throws Exception {
        Mockito.when(bookRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("api/books/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
