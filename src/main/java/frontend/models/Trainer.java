package frontend.models;

import frontend.Serialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trainer implements Serializable{
    private String name;
    private Sport specialization;
    private List<TrainingSession> trainingSessions;
    private TrainingSession trainingSession;


    public Trainer(String name, Sport specialization) {
        this.name = name;
        this.specialization = specialization;
        this.trainingSessions = new ArrayList<>();
    }


    public Trainer(String name, Sport specialization, TrainingSession trainingSession) {
        this.name = name;
        this.specialization = specialization;
        this.trainingSession = trainingSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sport getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Sport specialization) {
        this.specialization = specialization;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }

    public void save(List<Trainer> trainers) {
        Serialize.serializeObject(Serialize.daoRoute() + "trainers.txt", new ArrayList<>(trainers));
    }

    public void addSession(TrainingSession trainingSession){
        boolean exist = false;
        for (TrainingSession session : trainingSessions) {
            if (session.equals(trainingSession)) {
                exist = true;
                break;
                
            }
            
        }

        if (exist == false) {
            trainingSessions.add(trainingSession);
            
        }
        
    }
    //metodo para remover sesiones, verifica que la lista sesiones no esta vacia y luego si la sesion existe, si esto pasa remueve la sesion de la lista de sesiones
    public void removeSession(TrainingSession trainingSession) {
        if (trainingSessions != null) {

            boolean exist = false;
            for (TrainingSession session : trainingSessions) {
                if (session.equals(trainingSession)) {
                    exist = true;

                }

            }

            if (exist == true) {
                trainingSessions.remove(trainingSession);

            }

        }
    
}
}


