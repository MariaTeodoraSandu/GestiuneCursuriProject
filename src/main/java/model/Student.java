package model;

import org.example.BugetInvalidException;

import java.util.Objects;

public class Student {

    private int studentId;
    private String studentName;
    private double budget;

    public Student (int studentId, String studentName, double budget)
    {
        this.studentId=studentId;
        this.studentName=studentName;
        this.budget=budget;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) throws BugetInvalidException {
        if(budget < 0){
            throw new BugetInvalidException("Bugetul studentului nu este valid!");
        }
        this.budget = budget;
    }

    @Override
    public String toString() {
        return studentId+","+studentName+","+budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId && Double.compare(student.budget, budget) == 0 && Objects.equals(studentName, student.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, budget);
    }
}
