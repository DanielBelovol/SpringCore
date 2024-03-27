package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteDeleteAndUpdateResponse {
    private boolean success;
    private String error;
}
