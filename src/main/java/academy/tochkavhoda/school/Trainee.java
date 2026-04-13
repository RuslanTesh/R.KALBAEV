package academy.tochkavhoda.school;

import java.util.Objects;

public class Trainee {
    private String firstName;
    private String lastName;
    private int rating;

    public Trainee(String firstName, String lastName, int rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getRating() { return rating; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setRating(int rating) { this.rating = rating; }

    public String getFullName() { return firstName + " " + lastName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee t = (Trainee) o;
        return rating == t.rating &&
                Objects.equals(firstName, t.firstName) &&
                Objects.equals(lastName, t.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, rating);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + rating + ")";
    }
}