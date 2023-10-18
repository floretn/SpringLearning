package test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.dao.entity.Employee;
import test.dao.entity.EmployeeTask;
import test.services.HQLTestService;

import java.util.List;

@RestController
public class HQLTestController {
    private final HQLTestService service;

    public HQLTestController(HQLTestService service) {
        this.service = service;
    }

    @GetMapping("/hql/test/list")
    public List<Employee> getList() {
        return service.getAllEmployees();
    }

    @GetMapping("/hql/test/list_more_0")
    public List<String> getUserNamesMore0() {
        return service.getUserNamesMore0();
    }

    @GetMapping("/hql/test/last")
    public Employee getLast() {
        return service.last();
    }

    @GetMapping("/hql/test/get/{id}")
    public Employee getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @GetMapping("/hql/test/update_salary")
    public int updateSalary() {
        return service.updateSalary();
    }

    @GetMapping("/hql/test/get_et")
    public List<EmployeeTask> getEmployeeTaskForFirstUser() {
        return service.getEmployeeTaskForFirstUser();
    }

    @GetMapping("/hql/test/get_ot")
    public List<Employee> getEmployeeOldTask() {
        return service.getEmployeeOldTask();
    }

    @GetMapping("/hql/test/all_task_to_1")
    public int allTasksTo1() {
        return service.allTasksTo1();
    }

    @GetMapping("/hql/test/get_et_in_names_list")
    public List<EmployeeTask> getETInEmployeeList() {
        return service.getETInEmployeeList();
    }

    @GetMapping("/hql/test/get_2_from_2_task")
    public List<EmployeeTask> get2From2Task() {
        return service.get2From2Task();
    }

    @GetMapping("/hql/test/cnt_task")
    public int cntER() {
        return service.cntER();
    }

    @GetMapping("/hql/test/update_name/{id}/{newName}")
    public int updateNameById(@PathVariable("id") int id, @PathVariable("newName") String newName) {
        return service.updateNameById(id, newName);
    }

    @GetMapping("/hql/test/test_task")
    public void testTask() {
        service.testTask();
    }
}
