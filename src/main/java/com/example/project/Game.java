package com.example.project;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Game {
    public String title;

    public String description;

    public String category;

    public String imagePath;



    public Game(String title, String description, String category, String imagePath) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.imagePath = imagePath;

    }


}
