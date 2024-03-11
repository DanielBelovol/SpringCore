package com.example.demo.service;
import com.example.demo.repository.NoteRepository;
import com.example.demo.entity.Note;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> listAll(){
        return repository.findAll();
    }
    public Note add(Note note){
        return repository.save(note);
    }
    public void update(Note note){
        repository.save(note);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Note getById(Long id){
        return repository.findById(id).orElse(null);
    }
}
