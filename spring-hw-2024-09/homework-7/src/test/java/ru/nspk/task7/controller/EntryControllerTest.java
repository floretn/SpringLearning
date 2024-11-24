package ru.nspk.task7.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.nspk.task7.controller.dto.TransactionDto;
import ru.nspk.task7.model.AccountId;
import ru.nspk.task7.model.EntryId;
import ru.nspk.task7.service.AccountService;

@WebMvcTest(controllers = EntryController.class)
class EntryControllerTest {

    private static final String CREATE_URL = "/entry/create";
    private static final String REVERSE_URL = "/entry/%d/reverse";

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_create_success() throws Exception {
        when(accountService.makeEntry(any(), any(), anyLong())).thenReturn(new EntryId(123));

        TransactionDto testDto = new TransactionDto(1, 2, -100);

        String result = mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URL)
                        .content(packDtoToJson(testDto))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.contains("123"));
        verify(accountService).makeEntry(new AccountId(1), new AccountId(2), -100L);
    }

    @Test
    void test_create_error() throws Exception {
        when(accountService.makeEntry(any(), any(), anyLong())).thenThrow(new IllegalArgumentException("test"));

        TransactionDto testDto = new TransactionDto(1, 2, -100);

        String result = mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URL)
                        .content(packDtoToJson(testDto))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.contains("Request Error: test"));
        verify(accountService).makeEntry(new AccountId(1), new AccountId(2), -100L);
    }

    @Test
    void test_reverse_success() throws Exception {
        when(accountService.makeReverseEntry(any())).thenReturn(new EntryId(123));

        String result = mockMvc.perform(MockMvcRequestBuilders.put(REVERSE_URL.formatted(1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.contains("123"));
        verify(accountService).makeReverseEntry(new EntryId(1));
    }

    @Test
    void test_reverse_error() throws Exception {
        when(accountService.makeReverseEntry(any())).thenThrow(new IllegalArgumentException("test"));

        String result = mockMvc.perform(MockMvcRequestBuilders.put(REVERSE_URL.formatted(1)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.contains("Request Error: test"));
        verify(accountService).makeReverseEntry(new EntryId(1));
    }

    private String packDtoToJson(Object anObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(anObject);
    }
}
