package com.example.demo.service;


import com.example.demo.entity.Note;
import com.example.demo.request.NoteCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class NoteRequestValidator {
    public boolean validateCreateRequest(NoteCreateRequest request){
        return request.getTitle().length()>=3 &&
                request.getContent().length()>=3;
    }
    public boolean validateDeleteAndUpdateRequest(Note note){
        return validateCreateRequest(new NoteCreateRequest(note.getTitle(), note.getContent()));
    }
}
