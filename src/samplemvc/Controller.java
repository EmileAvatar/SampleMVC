/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samplemvc;

import java.awt.event.KeyEvent;
import javax.swing.DefaultListModel;

/**
 *
 * @author Administrator
 */
public class Controller {

    //<editor-fold defaultstate="collapsed" desc=" Main ">
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        controller = new Controller(new StudentView(), new Students());
        controller.view.setVisible(true);
    }
    //</editor-fold>

    private static Controller controller;
    private final StudentView view;
    private final Students model;

    public Controller(StudentView view, Students model) {
        this.view = view;
        this.model = model;
        init();
    }

    private void init() {
        this.view.setControl(this);
        try {
            for (int i = 0; i < 10; i++) {
                addStudent("Na" + i, "D" + i, i+20);
            }
            updateViewStudentList();
        } catch (Exception e) {
            System.out.println("Error Init: " + e);
        }
    }

    public boolean addStudent(String name, String surname, int age) {
        view.getjLabelStatusAdd().setText("");
        if (name == null || name.isEmpty()) {
            view.getjLabelStatusAdd().setText("Name cannot be empty");
            return  false;
        }
        if (surname == null || surname.isEmpty()) {
            view.getjLabelStatusAdd().setText("Surname cannot be empty");
            return  false;
        }
        if (age < 0 || age > 200) {
            view.getjLabelStatusAdd().setText("Age is invalid");
            return false;
        }
        
        Student s = new Student(name, surname, age);
        boolean add = model.add(s);
        view.getjLabelStatusAdd().setText("");
        view.getjTextFieldName().setText("");
        view.getjTextFieldSurname().setText("");
        updateViewStudentList();
        view.getjTextFieldName().requestFocus();
        return add;
    }
    
    public void updateViewStudentList() {
        DefaultListModel<String> list = view.getList();
        list.clear();
//        System.out.println(model.getStudents().size());
        int count = 1;
        for (Student student : model.getStudents()) {
            list.addElement(count + ": " + student.toString());
            count++;
        }
        
    }
    
    public void viewStudent(int index) {
        try {
            Student s = model.getStudents().get(index);
            view.getjLabelName().setText("Name: " + s.getFirstName());
            view.getjLabelSurname().setText("Surname: " + s.getLastName());
            view.getjLabelAge().setText("Age: " + s.getAge());
        } catch (Exception e) {
            view.getjLabelName().setText("Name:");
            view.getjLabelSurname().setText("Surname:");
            view.getjLabelAge().setText("Age:");
        }
    }

    public void updateStudentListKeyCode(int index, int keyCode) {
        // TODO - Add your code here
        try {
            if (keyCode == KeyEvent.VK_DELETE) {
                model.getStudents().remove(index);
                updateViewStudentList();
                if (index > 0) {
                    view.getjListStudents().ensureIndexIsVisible(index-1);
                    view.getjListStudents().setSelectedIndex(index-1);
                }
                if (index == 0 && model.getStudents().size() > 0) {
                    view.getjListStudents().ensureIndexIsVisible(0);
                    view.getjListStudents().setSelectedIndex(0);
                }
            }
        } catch (Exception e) {
        }
    }

    
}
