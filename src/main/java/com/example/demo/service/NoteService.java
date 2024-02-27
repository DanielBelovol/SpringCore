package com.example.demo.service;
import com.example.demo.entity.Note;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NoteService {
    Map<Long,Note> mapOfAllNotes = new HashMap();
    public List<Note> listAll(){
        List list = new ArrayList(mapOfAllNotes.values());
        return list;
    }
    public Note add(Note note){
        Note newNote = new Note(note.getTitle(), note.getContent());
        mapOfAllNotes.put(newNote.getId(), newNote);

        return newNote;
    }
    public void deleteById(Long id){
        mapOfAllNotes.remove(id);
    }

    public Note getById(Long id){
        return mapOfAllNotes.get(id);
    }

}
