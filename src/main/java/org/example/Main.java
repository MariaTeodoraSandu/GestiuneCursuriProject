package org.example;

import com.itfactory.data.DataLoader;
import com.itfactory.data.DataSaver;
import model.Course;
import model.Student;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        //incarcam datele din fisier
        DataLoader dl = new DataLoader();
        dl.loadData();
        Map<Course, List<Student>> date =  dl.getDataMap();
        DataSaver ds = new DataSaver();
        ds.saveData(date);
        Metode metode = new Metode();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Puteti introduce urmatoarele optiuni:\n" +
                "0 – Ies din program.\n" +
                "1 – Afiseaza cursuri\n" +
                "2 – Introduceti un curs nou\n" +
                "3 – Introduceti un student nou si inrolati-l la curs\n" +
                "4 – Cautati un student dupa nume (OPTIONAL)\n" +
                "5 – Afiseaza studentii si cursul la care participa. (OPTIONAL)");

while(true) {
    System.out.print("Introduceti optiunea dorita: ");
    int optiune = Integer.parseInt(scanner.nextLine());
    switch (optiune) {
        case 0:
            System.exit(0);
            break;
        case 1:
            Metode.afiseazaCursuri(date);
            break;
        case 2:
            metode.adaugCurs(date);
            Metode.afiseazaCursuri(date);
            ds.saveData(date);
            break;
        case 3:
            metode.adaugStudent(date);
            ds.saveData(date);
            break;
        case 4:
            System.out.println("Caut student dupa nume ");
            metode.cautStudent(date);
            break;
        case 5:
            metode.afiseazaStudentiSiCurs(date);
            break;
        default:
            System.out.println("Aceasta optiune nu exista");
            break;

        }
    }
    }
}