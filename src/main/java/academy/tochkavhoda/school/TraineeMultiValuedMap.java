package academy.tochkavhoda.school;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.Collection;

public class TraineeMultiValuedMap {
    private MultiValuedMap<Integer, Trainee> map;

    public TraineeMultiValuedMap() {
        this.map = new HashSetValuedHashMap<>();
    }

    public boolean addTrainee(Trainee trainee) {
        return map.put(trainee.getRating(), trainee);
    }

    public boolean removeTrainee(Trainee trainee) {
        return map.removeMapping(trainee.getRating(), trainee);
    }

    public Collection<Trainee> getTraineesByRating(int rating) {
        return map.get(rating);
    }

    public boolean hasAnyBodyRating(int rating) {
        return map.containsKey(rating);
    }

    public int getTraineesCount() {
        return map.size();
    }
}