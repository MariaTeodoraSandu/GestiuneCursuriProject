package com.itfactory.data;

import com.itfactory.utils.DataUtils;
import model.Course;
import model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataSaver {

    public void saveData(Map<Course, List<Student>> dataMap) throws IOException {
        saveCourses(dataMap);
        saveStudents(dataMap);
        saveMapping(dataMap);
    }

    private void saveStudents(Map<Course, List<Student>> dataMap) throws IOException {
        String continut = "";
        for(List<Student> listaStudenti : dataMap.values()){
            for(Student studentDinLista : listaStudenti){
                continut = continut.concat(studentDinLista.toString().concat("\n"));
            }
            DataUtils.writeFile(DataUtils.STUDENT_FILE_PATH_SAVE,continut);
        }
    }

    private void saveCourses(Map<Course, List<Student>> dataMap) throws IOException {
        Set<Course> setCursuri = dataMap.keySet();
        String continut = "";
        for(Course curs : setCursuri){
            continut = continut.concat(curs.toString().concat("\n"));
        }
        DataUtils.writeFile(DataUtils.COURSE_FILE_PATH,continut);
    }

    private void saveMapping(Map<Course, List<Student>> dataMap) throws IOException {
        String continut = "";
        for(Map.Entry<Course, List<Student>> bucata : dataMap.entrySet()){
            int idCurs = bucata.getKey().getCourseId();
            for(Student listaStudent : bucata.getValue()){
                int idStudent = listaStudent.getStudentId();
                continut = continut.concat(idStudent+","+idCurs+"\n");
            }
        }
        DataUtils.writeFile(DataUtils.MAPPING_FILE_PATH,continut);
    }

}
