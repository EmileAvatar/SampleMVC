/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplemvc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Students {
    private final List<Student> STUDENTS = new ArrayList<>();

    public Students() {
    }

    public List<Student> getStudents() {
        return STUDENTS;
    }

    public boolean add(Student e) {
        if (e == null) {
            return false;
        }
        return STUDENTS.add(e);
    }

}
