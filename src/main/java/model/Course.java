package model;

import java.time.LocalDate;
import java.util.Objects;

public class Course {

    private int courseId;
    private String courseName;
    private double price;
    private LocalDate startDate;

    public Course(int courseId, String courseName, double price, LocalDate startDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.price = price;
        this.startDate = startDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {

        this.startDate = startDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId && Double.compare(course.price, price) == 0 && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, price, startDate);
    }

    @Override
    public String toString() {
        return courseId +","+courseName+","+price+","+startDate;
    }
}
