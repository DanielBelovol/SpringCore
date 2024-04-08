package com.example.demo;

import com.example.demo.request.NoteCreateRequest;
import com.example.demo.service.NoteRequestValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class NoteRequestValidatorTest {
    private NoteRequestValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new NoteRequestValidator();
    }

    @Test
    public void testValidateCreateRequestWithValidData() {
        NoteCreateRequest validRequest = new NoteCreateRequest("Title", "Content");
        Assertions.assertTrue(validator.validateCreateRequest(validRequest),
                "Validator should return true for valid title and content lengths");
    }

    @Test
    public void testValidateCreateRequestWithInvalidData() {
        NoteCreateRequest invalidRequest = new NoteCreateRequest("Ti", "Co");
        Assertions.assertFalse(validator.validateCreateRequest(invalidRequest),
                "Validator should return false for title and content lengths less than 3");
    }
}
