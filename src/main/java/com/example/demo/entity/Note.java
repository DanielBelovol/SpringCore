package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Note {
    private static final AtomicLong idCounter = new AtomicLong();

    private Long id;
    String title;
    String content;


    public Note(){
        id = idCounter.incrementAndGet();
    }
    public Note(String title, String content){
        id = idCounter.incrementAndGet();
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
