package academy.tochkavhoda.school;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestTraineeBidiMap {

    @Test
    public void testAddTraineePassport() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        traineeBidiMap.addTrainee(trainee2, "123457");
        assertEquals(2, traineeBidiMap.getTraineesCount());
    }

    @Test
    public void testAddDuplicateTrainee() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Иван", "Иванов", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        try {
            traineeBidiMap.addTrainee(trainee2, "123457");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.DUPLICATE_TRAINEE, ex.getErrorCode());
        }
    }

    @Test
    public void testAddDuplicatePassport() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Иванов", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        try {
            traineeBidiMap.addTrainee(trainee2, "123456");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.DUPLICATE_PASSPORT, ex.getErrorCode());
        }
    }

    @Test
    public void testReplaceTraineePassport() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee, "123456");
        assertEquals(1, traineeBidiMap.getTraineesCount());
        assertEquals("123456", traineeBidiMap.getPassportByTrainee(trainee));
        traineeBidiMap.replaceTraineePassport(trainee, "123457");
        assertEquals(1, traineeBidiMap.getTraineesCount());
        assertEquals("123457", traineeBidiMap.getPassportByTrainee(trainee));
    }

    @Test
    public void testReplaceTraineePassportSetAnotherTraineePassport() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Иванов", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        traineeBidiMap.addTrainee(trainee2, "123457");
        assertEquals(2, traineeBidiMap.getTraineesCount());
        try {
            traineeBidiMap.replaceTraineePassport(trainee1, "123456");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.DUPLICATE_PASSPORT, ex.getErrorCode());
        }

    }

    @Test
    public void testReplaceNonExistentTraineePassport() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        try {
            traineeBidiMap.replaceTraineePassport(trainee, "123456");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
        }
    }


    @Test
    public void testRemoveTrainee() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        traineeBidiMap.addTrainee(trainee2, "123457");
        assertEquals(2, traineeBidiMap.getTraineesCount());
        traineeBidiMap.removeTrainee(trainee2);
        assertEquals(1, traineeBidiMap.getTraineesCount());
    }
    @Test
    public void testRemoveTraineeByPassport() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        traineeBidiMap.addTrainee(trainee2, "123457");
        assertEquals(2, traineeBidiMap.getTraineesCount());
        traineeBidiMap.removeTraineeByPassport("123456");
        assertEquals(1, traineeBidiMap.getTraineesCount());
    }

    @Test
    public void testRemoveNonExistentTrainee() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "ОмГУ");
        try {
            traineeBidiMap.removeTrainee(trainee2);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
        }
    }

    @Test
    public void testGetPassportByTrainee() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        TraineeBidiMap traineesMap = new TraineeBidiMap();
        traineesMap.addTrainee(trainee, "123456");
        String passport = traineesMap.getPassportByTrainee(trainee);
        assertEquals("123456", passport);
    }

    @SuppressWarnings("unused")
    @Test
    public void testGetPassportByNonExistentTrainee() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        try {
            String passport = traineeBidiMap.getPassportByTrainee(trainee2);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());

        }
    }

    @Test
    public void testGetTraineeByPassport() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        TraineeBidiMap traineesBidiMap = new TraineeBidiMap();
        traineesBidiMap.addTrainee(trainee, "123456");
        Trainee trainee1 = traineesBidiMap.getTraineeByPassport("123456");
        assertEquals(trainee, trainee1);
    }

    @SuppressWarnings("unused")
    @Test
    public void testGetTraineeByNonExistentPassport() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        try {
            Trainee trainee = traineeBidiMap.getTraineeByPassport("123457");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.PASSPORT_NOT_FOUND, ex.getErrorCode());

        }
    }

    @Test
    public void testGetAllTrainees() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        traineeBidiMap.addTrainee(trainee2, "123457");
        Set<Trainee> set = traineeBidiMap.getAllTrainees();
        assertEquals(2, set.size());
        assertTrue(set.contains(trainee1));
        assertTrue(set.contains(trainee2));
    }

    @Test
    public void testGetAllPassports() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        Trainee trainee3 = new Trainee("Николай", "Николаев", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        traineeBidiMap.addTrainee(trainee2, "123457");
        traineeBidiMap.addTrainee(trainee3, "123458");
        Set<String> institutes = traineeBidiMap.getAllpassports();
        assertEquals(3, institutes.size());
    }

    @Test
    public void testPassportExists() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        Trainee trainee3 = new Trainee("Николай", "Николаев", 1);
        TraineeBidiMap traineeBidiMap = new TraineeBidiMap();
        traineeBidiMap.addTrainee(trainee1, "123456");
        traineeBidiMap.addTrainee(trainee2, "123457");
        traineeBidiMap.addTrainee(trainee3, "123458");
        assertTrue(traineeBidiMap.hasAnybodyPassport("123456"));
        assertTrue(traineeBidiMap.hasAnybodyPassport("123457"));
        assertTrue(traineeBidiMap.hasAnybodyPassport("123458"));
    }
}
