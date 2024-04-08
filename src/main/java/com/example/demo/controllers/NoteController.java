package com.example.demo.controllers;

import com.example.demo.entity.Note;
import com.example.demo.request.NoteCreateRequest;
import com.example.demo.response.NoteDeleteAndUpdateResponse;
import com.example.demo.response.NoteFindResponse;
import com.example.demo.response.NoteResponse;
import com.example.demo.service.NoteRequestValidator;
import com.example.demo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {
    private static final String OK_MESSAGE = "Successful";
    private static final String SERVER_BAD_MESSAGE = "server error";
    private static final String CONTENT_BAD_MESSAGE = "content or title is invalid";
    private static final String NO_FOUND_NOTE_MESSAGE = "note not found by id";


    private final NoteService service;
    private final NoteRequestValidator requestValidator;

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteCreateRequest request) {
        NoteResponse noteResponse;
        HttpStatus status;
        try {
            if (requestValidator.validateCreateRequest(request)) {
                service.add(new Note(request.getTitle(), request.getContent()));
                status = HttpStatus.OK;
                noteResponse = new NoteResponse(true, OK_MESSAGE);
            } else {
                status = HttpStatus.BAD_REQUEST;
                noteResponse = new NoteResponse(false, CONTENT_BAD_MESSAGE);
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            noteResponse = new NoteResponse(false, SERVER_BAD_MESSAGE);
        }
        return new ResponseEntity<>(noteResponse, status);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<NoteFindResponse> getById(@PathVariable Long id) {
        NoteFindResponse noteResponse;
        HttpStatus status;
        try {
            Note note = service.getById(id);
            status = HttpStatus.OK;
            noteResponse = new NoteFindResponse(note, true, OK_MESSAGE);
        } catch (NoSuchElementException nse) {
            noteResponse = new NoteFindResponse(null, false, NO_FOUND_NOTE_MESSAGE);
            status = HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            noteResponse = new NoteFindResponse(null, false, SERVER_BAD_MESSAGE);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(noteResponse, status);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<NoteDeleteAndUpdateResponse> deleteById(@PathVariable Long id) {
        NoteDeleteAndUpdateResponse noteResponse;
        HttpStatus status;
        try {
            service.deleteById(id);
            status = HttpStatus.OK;
            noteResponse = new NoteDeleteAndUpdateResponse(true, OK_MESSAGE);
        } catch (NoSuchElementException nse) {
            status = HttpStatus.NOT_FOUND;
            noteResponse = new NoteDeleteAndUpdateResponse(false, NO_FOUND_NOTE_MESSAGE);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            noteResponse = new NoteDeleteAndUpdateResponse(false, SERVER_BAD_MESSAGE);
        }
        return new ResponseEntity<>(noteResponse, status);
    }

    @PutMapping("/update")
    public ResponseEntity<NoteDeleteAndUpdateResponse> update(@RequestBody Note note) {
        NoteDeleteAndUpdateResponse noteResponse;
        HttpStatus status;
        try {
            if (requestValidator.validateDeleteAndUpdateRequest(note)) {
                service.update(note);
                status = HttpStatus.OK;
                noteResponse = new NoteDeleteAndUpdateResponse(true, OK_MESSAGE);
            } else {
                status = HttpStatus.BAD_REQUEST;
                noteResponse = new NoteDeleteAndUpdateResponse(false, CONTENT_BAD_MESSAGE);
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            noteResponse = new NoteDeleteAndUpdateResponse(false, SERVER_BAD_MESSAGE);
        }
        return new ResponseEntity<>(noteResponse, status);
    }
}
