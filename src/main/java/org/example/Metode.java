package org.example;

import model.Course;
import model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Metode {

    Scanner scanner = new Scanner(System.in);
    public static void afiseazaCursuri(Map<Course, List<Student>> dataMap){
        System.out.println("Cursurile disponibile sunt: ");
        Set<Course> setCursuri = dataMap.keySet();
        for(Course element: setCursuri){
            System.out.println(element);
        }
    }

    public void adaugCurs(Map<Course, List<Student>> dataMap) {
        System.out.print("Introduceti id-ul cursului: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduceti numele cursului: ");
        String nume = scanner.nextLine();
        System.out.print("Introduceti pretul cursului: ");
        double pret = Double.parseDouble(scanner.nextLine());
        System.out.print("Introduceti data de inceput a cursului in formatul dd/MM/yyyy: ");
        String dataString = scanner.nextLine();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataCurs = LocalDate.parse(dataString, format);

        Course cursNou = new Course(id, nume, pret, dataCurs);
        dataMap.put(cursNou, new ArrayList<>());
    }

    public void adaugStudent(Map<Course, List<Student>> dataMap){

        int cursuriDisponibile = 0;

        for(Map.Entry<Course, List<Student>> portiune: dataMap.entrySet()) {
                if (portiune.getValue().size() < 9) {
                    System.out.println("CURS disponibil: " + portiune.getKey());

                    cursuriDisponibile++;
                }

        }

        if(cursuriDisponibile == 0) {
            System.out.println("Ne pare rau, nu avem niciun curs disponibil");
        } else {

            System.out.print("Introduceti numele cursului la care doriti sa adaugati studentul: ");
            String cursCautat = scanner.nextLine();

            Course cursGasit = null;
            LocalDate dataInrolareStudent = LocalDate.now();

            for (Course curs : dataMap.keySet()) {
                if (curs.getCourseName().equalsIgnoreCase(cursCautat) && dataInrolareStudent.isBefore(curs.getStartDate()) ) {
                    System.out.println("Am gasit cursul!");
                    cursGasit = curs;
                    break;
                }
            }

            //ca sa evitam exceptiile nullpointer, avem grija sa nu lucram mai departe cu un curs daca este null
            if (cursGasit == null) {
                System.out.println("Acest curs nu exista!");
            } else {

                System.out.print("Introduceti id-ul studentului: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Introduceti numele studentului: ");
                String nume = scanner.nextLine();
                System.out.print("Introduceti bugetul studentului: ");
                double buget = Double.parseDouble(scanner.nextLine());

                Student studentNou = new Student(id, nume, buget);

                System.out.println("Ati introdus urmatorul student: " + studentNou);

                try {
                    studentNou.setBudget(buget - cursGasit.getPrice());
                    dataMap.get(cursGasit).add(studentNou);
                    for(Map.Entry<Course, List<Student>> portiune: dataMap.entrySet()){
                        System.out.println(portiune);
                    }
                } catch (BugetInvalidException e) {
                    System.out.println("Ne pare rau, studentul nu are suficienti bani");
                }
            }
        }
    }

    public void cautStudent(Map<Course, List<Student>> dataMap){
        System.out.print("Introduceti numele studentului pe care il cautati: ");
        String nume = scanner.nextLine();
        for(List<Student> listaStudent: dataMap.values()){
            for(Student element: listaStudent ){
                if(element.getStudentName().equalsIgnoreCase(nume)){
                    System.out.println("Am gasit studentul: " +element);
                }
            }
        }
    }

    public void afiseazaStudentiSiCurs(Map<Course, List<Student>> dataMap){
        for(Course curs: dataMap.keySet()){
            System.out.println("La cursul: " +curs.getCourseName() + " participa urmatorii studenti: ");
             for(Student studenti: dataMap.get(curs)) {
                 System.out.println(studenti.getStudentName());
             }
            System.out.println("\n");
        }
    }
}
