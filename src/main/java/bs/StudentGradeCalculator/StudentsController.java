package bs.StudentGradeCalculator;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;

@Controller
public class StudentsController {

    private HashMap<Integer, Student> myStudents = new HashMap<>();
    private int count = 1000;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/addstudent")
    public String student() {
        return "addstudent.html";
    }

    @PostMapping("/addstudent")
    public void addStudent(@RequestParam String studentName, @RequestParam String course, @RequestParam String gpa, HttpServletResponse response) {

        double gpa_num = Double.parseDouble(gpa);

        Student newStudent = new Student();

        newStudent.setStudentNumber(count);
        newStudent.setStudentName(studentName);
        newStudent.setCourse(course);
        newStudent.setGpa(gpa_num);
        myStudents.put(newStudent.getStudentNumber(), newStudent);

        count++;

        try {
            response.sendRedirect("/browsestudents");
        } catch (IOException e) {e.printStackTrace();}
    }

    @GetMapping("/browsestudents")
    public String browseStudents(Model model) {
        model.addAttribute("students", myStudents.values());
        return "browsestudents.html";
    }
}
