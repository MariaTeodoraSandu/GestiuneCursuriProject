package com.itfactory.data;

import com.itfactory.utils.DataUtils;
import model.Course;
import model.Student;
import org.example.BugetInvalidException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {
    private Map<Course, List<Student>> dataMap;
    private List<Student> students;

    public DataLoader() {
        dataMap = new HashMap<>(); //chei=>cursuri, valori=>lista de studenti
        students = new ArrayList<>();

    }

    public void loadData() throws IOException {
        loadCourses();
        loadStudents();
        for(Student element: students) {

            System.out.println(element);
        }
        mapStudentsToCourses();
        System.out.println(dataMap);
    }

    private void loadCourses() throws IOException {
        List<String> courseInputData = DataUtils.readFile(DataUtils.COURSE_FILE_PATH);
        System.out.println(courseInputData);

        for (String courseInputDatum : courseInputData) {
            String[] data = courseInputDatum.split(",");
            dataMap.put(createCourse(data), new ArrayList<>());
        }
    }

    private Course createCourse(String[] data) {
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        double price = Double.parseDouble(data[2]);
        LocalDate date = LocalDate.parse(data[3]);
        return new Course(id, name, price,date);
    }

    private void loadStudents() throws IOException {
        List<String>studentInputData = DataUtils.readFile(DataUtils.STUDENT_FILE_PATH);
        System.out.println(studentInputData);
        for(String studentInputDatum: studentInputData){
            String[] dateStudenti = studentInputDatum.split(",");
            students.add(createStudent(dateStudenti));

        }

    }

    private Student createStudent(String[] dateStudenti){
        int id = Integer.parseInt(dateStudenti[0]);
        String name = dateStudenti[1];
        double budget = Double.parseDouble(dateStudenti[2]);
        return new Student(id,name, budget);
    }

    private void mapStudentsToCourses() throws IOException {
        List<String>listaMapare = DataUtils.readFile(DataUtils.MAPPING_FILE_PATH);
        for(String linie: listaMapare)
        {
           String[] iduri = linie.split(",");

           int idStudent = Integer.parseInt(iduri[0]);
           int idCurs = Integer.parseInt(iduri[1]);

           Student studentGasit = null;
           Course cursGasit = null;
           for(Student element: students){
               if(element.getStudentId() == idStudent){
                   studentGasit = element;
                   break;
               }
           }

           for(Course element: dataMap.keySet()){
               if(element.getCourseId() == idCurs){
                   cursGasit = element;
                   break;
               }
           }
           if(cursGasit != null && studentGasit != null) {

               double bugetStudent = studentGasit.getBudget();
               double pretCurs = cursGasit.getPrice();
               double bugetRamas = bugetStudent - pretCurs;

               try {
                   studentGasit.setBudget(bugetRamas);
                   //doar daca buget >= pretul cursului, are sens sa adaug in map studentul si fac urmat operatie
                   dataMap.get(cursGasit).add(studentGasit); // in map-ul meu identific cursul pe care l-am gasit (cu get), la care add studentul gasit
               } catch (BugetInvalidException e) {
                   System.out.println("Studentul nu poate fi adaugat, deoarece nu are suficienti bani");
               }
           }
        }
    }

    public Map<Course, List<Student>> getDataMap() {
        return dataMap;
    }


}
