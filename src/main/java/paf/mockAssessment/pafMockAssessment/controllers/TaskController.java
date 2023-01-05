package paf.mockAssessment.pafMockAssessment.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import paf.mockAssessment.pafMockAssessment.models.Task;
import paf.mockAssessment.pafMockAssessment.models.User;
import paf.mockAssessment.pafMockAssessment.services.TodoException;
import paf.mockAssessment.pafMockAssessment.services.TodoService;

@Controller
@RequestMapping(path="/todo")
public class TaskController {

    @Autowired
    private TodoService todoSvc;
    
    @PostMapping
    public String postTodo(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session) throws ParseException, TodoException {
        
        List<Task> tasks = (List<Task>) session.getAttribute("tasks");
        if (tasks == null) {
            System.out.println("this is a new todo list");
            System.out.printf("Session id = %s\n", session.getId());
            tasks = new LinkedList<>();
            session.setAttribute("tasks", tasks);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
        User user = new User();
        user.setUsername(form.getFirst("username"));
        user.setFullName(form.getFirst("fullName"));
        System.out.println(user.getUsername());

        //do the tasks
        String description = form.getFirst("description");
        int priority = Integer.parseInt(form.getFirst("priority"));
        // Date dueDate = formatter.parse(form.getFirst("dueDate"));
        String dueDate = form.getFirst("dueDate");
        tasks.add(new Task(description, priority, dueDate));

        user.setTasks(tasks);
        
        session.setAttribute("user", user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);

        return "index_template";
    }

    @PostMapping(path="/delete")
    public String removeTask(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session) throws ParseException, TodoException {
        
        //Load user and tasks list
        List<Task> tasks = (List<Task>) session.getAttribute("tasks");
        User user = (User) session.getAttribute("user");
        System.out.println(user.getUsername());

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
        

        //do the tasks
        String description = form.getFirst("description");
        System.out.println(description);
        int priority = Integer.parseInt(form.getFirst("priority"));
        System.out.println(priority);
        // Date dueDate = formatter.parse(form.getFirst("dueDate"));
        String dueDate = form.getFirst("dueDate");
        System.out.println(dueDate);


        for (int i =0; i< tasks.size(); i++) {
            if((tasks.get(i).getDescription().equals(description)) && (tasks.get(i).getPriority()==priority) && (tasks.get(i).getDueDate().equals(dueDate))) {
                System.out.println("delete from list");
                tasks.remove(tasks.get(i));
            }
        }

        user.setTasks(tasks);
        
        session.setAttribute("user", user);
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);

        return "index_template";
    }
    
    @PostMapping(path="/save")
    public String postSave(Model model, HttpSession session) throws TodoException {
        List<Task> tasks = (List<Task>) session.getAttribute("tasks");
        User user = (User) session.getAttribute("user");
        todoSvc.upsertTask(user);

        session.invalidate();
        String success="Task was added successfully! Thanks for using our applciation!";
        model.addAttribute("success", success);

        return "save";
    }

}
