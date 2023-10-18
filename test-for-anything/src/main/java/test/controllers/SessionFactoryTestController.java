package test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.dao.entity.Employee;
import test.services.SessionFactoryTestService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SessionFactoryTestController {
    private final SessionFactoryTestService service;

    public SessionFactoryTestController(SessionFactoryTestService service) {
        this.service = service;
    }

    @GetMapping("/session-factory/test/list")
    public List<Employee> getList() {
        return service.getAllEmployees();
    }

    @GetMapping("/session-factory/test/get/{id}")
    public Employee getEmpl(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @GetMapping("/session-factory/test/add-two")
    public List<Employee> addTwo() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(0, "Two1", 1));
        list.add(new Employee(0, "Two2", 2));
        service.addEmployee(list);
        return service.getAllEmployees();
    }

    @GetMapping("/session-factory/test/add-three")
    public List<Employee> addThree() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(0, "Three1", 1));
        list.add(new Employee(0, "Three2", 2));
        list.add(new Employee(0, "Three3", 2));
        service.addEmployee(list);
        return service.getAllEmployees();
    }

    @GetMapping("/session-factory/test/transaction")
    public String transaction() {
        return service.testTransaction();
    }
}
