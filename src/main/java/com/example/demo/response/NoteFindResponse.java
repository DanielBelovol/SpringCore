package com.example.demo.response;


import com.example.demo.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteFindResponse {
    private Note note;
    private boolean success;
    private String error;
}
