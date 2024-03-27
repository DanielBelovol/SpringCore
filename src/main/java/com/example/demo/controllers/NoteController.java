package com.example.demo.controllers;

import com.example.demo.entity.Note;
import com.example.demo.request.NoteCreateRequest;
import com.example.demo.response.NoteResponse;
import com.example.demo.service.NoteRequestValidator;
import com.example.demo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteRestController {

    private final NoteService service ;
    private final NoteRequestValidator requestValidator;

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteCreateRequest request){
        NoteResponse noteResponse;
        HttpStatus status;
        if(requestValidator.validateCreateRequest(request)){
            service.add(new Note(request.getTitle(),request.getContent()));
            status = HttpStatus.OK;
            noteResponse = new NoteResponse(true,"Success created")
        }else {
            status = HttpStatus.BAD_REQUEST;

        }
    }

}
