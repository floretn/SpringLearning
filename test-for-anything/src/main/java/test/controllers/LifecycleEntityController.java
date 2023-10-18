package test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dao.entity.Employee;
import test.dao.entity.Test;
import test.services.LifecycleEntityService;

import java.util.List;

@RestController
public class LifecycleEntityController {

    private final LifecycleEntityService service;

    public LifecycleEntityController(LifecycleEntityService service) {
        this.service = service;
    }

    @GetMapping("/lifecycle/example")
    public String getList() {
        try {
            service.examples();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/lifecycle/tryToSave")
    public String tryToSave() {
        try {
            return service.tryToSave();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/lifecycle/update")
    public String update() {
        try {
            return service.update();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/lifecycle/remove")
    public String remove() {
        try {
            return service.remove();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/lifecycle/softRemove")
    public List<Test> softRemove() {
        try {
            return service.softRemove();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
