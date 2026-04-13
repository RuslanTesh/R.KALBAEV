package academy.tochkavhoda.school;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestTraineeMultiValuedMap {

    @Test
    public void testTraineeMultiValuedMap() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 5);
        Trainee trainee2 = new Trainee("Петр", "Петров", 4);
        Trainee trainee3 = new Trainee("Николай", "Николаев", 5);
        Trainee trainee4 = new Trainee("Федор", "Федоров", 4);
        Trainee trainee5 = new Trainee("Сидор", "Сидоров", 2);
        TraineeMultiValuedMap traineeMultiValuedMap = new TraineeMultiValuedMap();
        assertTrue(traineeMultiValuedMap.addTrainee(trainee1));
        assertFalse(traineeMultiValuedMap.addTrainee(trainee1));
        assertTrue(traineeMultiValuedMap.addTrainee(trainee2));
        assertTrue(traineeMultiValuedMap.addTrainee(trainee3));
        assertTrue(traineeMultiValuedMap.addTrainee(trainee4));
        assertTrue(traineeMultiValuedMap.addTrainee(trainee5));
        assertEquals(5, traineeMultiValuedMap.getTraineesCount());
        assertEquals( 2, traineeMultiValuedMap.getTraineesByRating(5).size());
        assertEquals( 2, traineeMultiValuedMap.getTraineesByRating(4).size());
        assertEquals( 1, traineeMultiValuedMap.getTraineesByRating(2).size());
        assertEquals( 0, traineeMultiValuedMap.getTraineesByRating(3).size());
        assertTrue(traineeMultiValuedMap.hasAnyBodyRating(2));
        assertFalse(traineeMultiValuedMap.hasAnyBodyRating(3));
        assertTrue(traineeMultiValuedMap.removeTrainee(trainee5));
        assertFalse(traineeMultiValuedMap.removeTrainee(trainee5));
        assertEquals( 0, traineeMultiValuedMap.getTraineesByRating(2).size());
        assertFalse(traineeMultiValuedMap.hasAnyBodyRating(2));
        traineeMultiValuedMap.removeTrainee(trainee1);
        assertEquals( 1, traineeMultiValuedMap.getTraineesByRating(5).size());
        assertTrue(traineeMultiValuedMap.hasAnyBodyRating(5));
    }

}
