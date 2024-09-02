package frontend.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import frontend.Serialize;

public class TrainingSession implements Serializable {
        private LocalDate date;
        private int duration;
        private StateSession state;
        private Sport sport;
        private Trainer trainer;
        private List<Member> members;

        public TrainingSession(LocalDate date, int duration, StateSession state, Sport sport,
                Trainer trainer) {
            this.date = date;
            this.duration = duration;
            this.state = state;
            this.sport = sport;
            this.trainer = trainer;
            this.members = new LinkedList<>();
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public StateSession getState() {
            return state;
        }

        public void setState(StateSession state) {
            this.state = state;
        }

        public Sport getSport() {
            return sport;
        }

        public void setSport(Sport sport) {
            this.sport = sport;
        }

        public Trainer getTrainer() {
            return trainer;
        }

        public void setTrainer(Trainer trainer) {
            this.trainer = trainer;
        }

        public List<Member> getMembers() {
            return members;
        }

        public void setMembers(List<Member> members) {
            this.members = members;
        }

        public void save(List<TrainingSession> trainingSessions) {
        Serialize.serializeObject(Serialize.daoRoute() + "trainingSessions.txt", new ArrayList<>(trainingSessions));
    }

        public void addMember(Member member) {
            if (members != null && member != null) {
                members.add(member);
            }
        }
    
        public boolean isFinalized() {
            if (this.state.equals(StateSession.FINALIZED)) {
                return true;
            }
            return false;
        }
    }

