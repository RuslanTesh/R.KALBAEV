package academy.tochkavhoda.school;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {
    private Queue<Trainee> queue;

    public TraineeQueue() {
        this.queue = new LinkedList<>();
    }

    public void addTrainee(Trainee trainee) {
        queue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {
        if (queue.isEmpty()) throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        return queue.poll();
    }

    public boolean isEmpty() { return queue.isEmpty(); }
}