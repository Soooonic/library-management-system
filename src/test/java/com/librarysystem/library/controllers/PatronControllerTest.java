package com.librarysystem.library.controllers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.librarysystem.library.models.Patron;
import com.librarysystem.library.repositories.PatronRepository;
import com.librarysystem.library.services.PatronService;
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

@WebMvcTest(PatronController.class)
public class PatronControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired
    ObjectMapper objectMapper ;
    @MockBean
    private PatronService patronService;
    @Mock
    private PatronRepository patronRepository;
    Patron RECORD_1=new Patron("abdelrahman123","abdelrahman mohamed",21,"Egypt");
    Patron RECORD_2=new Patron("ahmed_1","ahmed Mohamed",24,"Egypt");
    Patron RECORD_3=new Patron("nour22","nour mohamed",24,"France");

    @Test
    public void createPatron_success() throws Exception {
        Patron record =new Patron("abdo123","abdo mohamed",21,"Egypt");
        Mockito.when(patronRepository.save(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/patrons")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.name", is("abdo mohamed")));
    }

    @Test
    public void getAllPatrons_success()throws Exception{
        List<Patron> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(patronRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[2].name", is("ahmed mohamed"))
                );
    }
    @Test
    public void getPatronById_success() throws Exception {
        Mockito.when(patronRepository.findById(RECORD_1.getId())).thenReturn(java.util.Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.name", is("abdelrahman mohamed")));
    }


    @Test
    public void updatePatron_success() throws Exception {
        Patron updatedRecord =new Patron("nour123","nour mohamed",21,"Egypt");

        Mockito.when(patronRepository.findById(RECORD_1.getId())).thenReturn(Optional.of(RECORD_1));
        Mockito.when(patronRepository.save(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("api/patrons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.name", is("nour mohamed")));
    }

    @Test
    public void deletePatronById_success() throws Exception {
        Mockito.when(patronRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("api/patrons/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
