package academy.tochkavhoda.school;

import java.util.*;

public class School {
    private String name;
    private int year;
    private Set<Group> groups;

    public School(String name, int year) throws TrainingException {
        if (name == null || name.isEmpty()) throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        this.name = name;
        this.year = year;
        this.groups = new HashSet<>();
    }

    public String getName() { return name; }
    public int getYear() { return year; }
    public Set<Group> getGroups() { return groups; }

    public void setName(String name) throws TrainingException {
        if (name == null || name.isEmpty()) throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        this.name = name;
    }

    public void setYear(int year) { this.year = year; }

    public void addGroup(Group group) throws TrainingException {
        boolean nameExists = groups.stream().anyMatch(g -> g.getName().equals(group.getName()));
        if (nameExists) throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
        groups.add(group);
    }

    public void removeGroup(Group group) throws TrainingException {
        if (!groups.remove(group)) throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public void removeGroup(String name) throws TrainingException {
        Group found = groups.stream()
                .filter(g -> g.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND));
        groups.remove(found);
    }

    public boolean containsGroup(Group group) {
        return groups.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School s = (School) o;
        return year == s.year &&
                Objects.equals(name, s.name) &&
                Objects.equals(groups, s.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, groups);
    }
}