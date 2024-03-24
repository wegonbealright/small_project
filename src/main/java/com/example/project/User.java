package com.example.project;


import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
public class User {
    public String username;

    public String password;

    public String email;

    public ArrayList<Game> gameList = new ArrayList<>();


    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        this.gameList.add(new Game("7 DAYS TO DIE",
                "7 Days to Die is an open-world game that is a unique combination of first-person shooter," +
                " survival horror, tower defense, and role-playing games. " +
                "Play the definitive zombie survival sandbox RPG that came first. " +
                "Navezgane awaits!",
                "survival horror", "src/main/resources/images/7DAYS.png"));
        this.gameList.add(new Game("Resident Evil 4",
                "Survival is just the beginning. Six years have passed since the biological disaster in Raccoon City." +
                " Leon S. Kennedy, one of the survivors, " +
                "tracks the president's kidnapped daughter to a secluded European village," +
                " where there is something terribly wrong with the locals.", "survival horror", "src/main/resources/images/RE4.png"));
        this.gameList.add(new Game("Borderlands 2",
                "The Ultimate Vault Hunter’s Upgrade lets you get the most out of the Borderlands 2 experience.",
                "first-person shooter", "src/main/resources/images/borderlands2.png"));
        this.gameList.add(new Game("Noita", "Noita is a magical action roguelite set in a world where every pixel is physically simulated." +
                " Fight, explore, melt, burn, freeze and evaporate your way through the procedurally generated world using spells you've created yourself.",
                "roguelike", "src/main/resources/images/noita.png"));
        this.gameList.add(new Game("Katana ZERO", "Katana ZERO is a stylish neo-noir, action-platformer featuring breakneck action" +
                " and instant-death combat." +
                " Slash, dash, and manipulate time to unravel your past in a beautifully brutal acrobatic display.",
                "action-platformer", "src/main/resources/images/katanaZERO.png"));
        this.gameList.add(new Game("VALORANT", "Valorant is an online multiplayer computer game, produced by Riot Games." +
                " It is a first-person shooter game, consisting of two teams of five, where one team attacks and the other defends.",
                "shooter", "src/main/resources/images/valorant.png"));
    }

    public User() {
    }


}