package academy.tochkavhoda.school;

import java.util.*;

public class Group {
    private String name;
    private String room;
    private List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {
        if (name == null || name.isEmpty()) throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        if (room == null || room.isEmpty()) throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.name = name;
        this.room = room;
        this.trainees = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getRoom() { return room; }
    public List<Trainee> getTrainees() { return trainees; }

    public void setName(String name) throws TrainingException {
        if (name == null || name.isEmpty()) throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        this.name = name;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.isEmpty()) throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.room = room;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!trainees.remove(trainee)) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTrainee(int index) throws TrainingException {
        if (index < 0 || index >= trainees.size()) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        trainees.remove(index);
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        return trainees.stream()
                .filter(t -> t.getFirstName().equals(firstName))
                .findFirst()
                .orElseThrow(() -> new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND));
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        return trainees.stream()
                .filter(t -> t.getFullName().equals(fullName))
                .findFirst()
                .orElseThrow(() -> new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND));
    }

    public void sortTraineeListByFirstNameAscendant() {
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        trainees.sort(Comparator.comparingInt(Trainee::getRating).reversed());
    }

    public void reverseTraineeList() {
        Collections.reverse(trainees);
    }

    public void rotateTraineeList(int positions) {
        Collections.rotate(trainees, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        if (trainees.isEmpty()) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        int maxRating = trainees.stream().mapToInt(Trainee::getRating).max().getAsInt();
        List<Trainee> result = new ArrayList<>();
        for (Trainee t : trainees) {
            if (t.getRating() == maxRating) result.add(t);
        }
        return result;
    }

    public boolean hasDuplicates() {
        Set<Trainee> seen = new HashSet<>();
        for (Trainee t : trainees) {
            if (!seen.add(t)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group g = (Group) o;
        return Objects.equals(name, g.name) &&
                Objects.equals(room, g.room) &&
                Objects.equals(trainees, g.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }
}