package frontend.models;

import frontend.Serialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Sport implements Serializable {
    private String name;
    private String description;
    private Difficulty difficulty;
    private Collection<Trainer> trainers;
    private Trainer trainer;

    public Sport(String name, String description, Difficulty difficulty, Collection<Trainer> trainers) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.trainers = trainers;
    }

    

    public Sport(String name, String description, Difficulty difficulty, Trainer trainer) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.trainer = trainer;
    }


    public Trainer getTrainer() {
        return trainer;
    }



    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Collection<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Collection<Trainer> trainers) {
        this.trainers = trainers;
    }

    public void save(List<Sport> sports) {
        Serialize.serializeObject(Serialize.daoRoute() + "sports.txt", new ArrayList<>(sports));
    }
}