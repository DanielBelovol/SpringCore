package com.example.demo.service.controllers;


import com.example.demo.entity.Note;
import com.example.demo.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/note")
public class FrontController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "list_notes"; // HTML page for listing notes
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        noteService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Note was deleted successfully.");
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNotePage(@RequestParam("id") Long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "edit_note"; // HTML page for editing a note
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute("note") Note note, RedirectAttributes redirectAttributes) {
        noteService.update(note);
        redirectAttributes.addFlashAttribute("message", "Note was updated successfully.");
        return "redirect:/note/list";
    }

    @GetMapping("/add")
    public String addNotePage(Model model) {
        model.addAttribute("note", new Note());
        return "add_note.html"; // HTML page for adding a new note
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute("note") Note note, RedirectAttributes redirectAttributes) {
        noteService.add(note);
        redirectAttributes.addFlashAttribute("message", "Note was added successfully.");
        return "redirect:/note/list";
    }
}
