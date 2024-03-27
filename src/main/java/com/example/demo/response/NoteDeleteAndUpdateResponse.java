package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteDeleteResponse {
    private boolean success;
    private String error;
}
