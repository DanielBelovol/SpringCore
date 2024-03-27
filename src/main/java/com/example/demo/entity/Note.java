package com.example.demo.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    String title;
    @Column
    String content;
    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }
}
