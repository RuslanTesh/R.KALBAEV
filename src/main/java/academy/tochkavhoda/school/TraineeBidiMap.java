package academy.tochkavhoda.school;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Set;

public class TraineeBidiMap {
    private BidiMap<String, Trainee> bidiMap;

    public TraineeBidiMap() {
        this.bidiMap = new DualHashBidiMap<>();
    }

    public void addTrainee(Trainee trainee, String passport) throws TrainingException {
        if (bidiMap.containsValue(trainee)) throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        if (bidiMap.containsKey(passport)) throw new TrainingException(TrainingErrorCode.DUPLICATE_PASSPORT);
        bidiMap.put(passport, trainee);
    }

    public void replaceTraineePassport(Trainee trainee, String passport) throws TrainingException {
        if (!bidiMap.containsValue(trainee)) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        if (bidiMap.containsKey(passport)) throw new TrainingException(TrainingErrorCode.DUPLICATE_PASSPORT);
        String oldPassport = bidiMap.getKey(trainee);
        bidiMap.remove(oldPassport);
        bidiMap.put(passport, trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!bidiMap.containsValue(trainee)) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        bidiMap.removeValue(trainee);
    }

    public void removeTraineeByPassport(String passport) throws TrainingException {
        if (!bidiMap.containsKey(passport)) throw new TrainingException(TrainingErrorCode.PASSPORT_NOT_FOUND);
        bidiMap.remove(passport);
    }

    public Trainee getTraineeByPassport(String passport) throws TrainingException {
        if (!bidiMap.containsKey(passport)) throw new TrainingException(TrainingErrorCode.PASSPORT_NOT_FOUND);
        return bidiMap.get(passport);
    }

    public String getPassportByTrainee(Trainee trainee) throws TrainingException {
        if (!bidiMap.containsValue(trainee)) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        return bidiMap.getKey(trainee);
    }

    public Set<Trainee> getAllTrainees() { return (Set<Trainee>) bidiMap.values(); }

    public Set<String> getAllpassports() { return bidiMap.keySet(); }

    public boolean hasAnybodyPassport(String passport) { return bidiMap.containsKey(passport); }

    public int getTraineesCount() { return bidiMap.size(); }
}