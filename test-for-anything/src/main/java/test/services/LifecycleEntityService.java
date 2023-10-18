package test.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import test.dao.entity.Employee;
import test.dao.entity.EmployeeTask;
import test.dao.entity.Test;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LifecycleEntityService {

    private final SessionFactory sessionFactory;
    public LifecycleEntityService(EntityManagerFactory factory) {
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void examples() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //Transient
        Employee employee = new Employee(0, "Test", 1);
        //Persistent (JPA spec) or Managed (Hibernate spec)
        session.save(employee);
        //Removed
        session.remove(employee);
        //Also Persistent or Managed
        employee = session.load(Employee.class, 1); //employee = proxy
        //Detached
        session.evict(employee);
        //Methods of employee wil throw Exceptions??? OR NOT????? WHAT??????
        System.out.println(employee.getId());
        transaction.commit();
        session.close();
        //Here all objects are Detached because session was closed
        System.out.println(employee.getId());
    }

    public String tryToSave() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //Transient
        Employee employee = session.find(Employee.class, 1);
        employee.setSalary(employee.getSalary() + 100);
        int i = (Integer) session.save(employee);
        System.out.println(i);
        session.evict(employee);
        //ERROR Detached entity
        session.persist(employee);
        transaction.commit();
        session.close();
        return "OK";
    }

    public String update() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //Transient
        Employee employee = session.find(Employee.class, 1);
        employee.setSalary(employee.getSalary() + 100);
        //Сохранится и без этого метода. Если employee не существует - создаст. Метод update бросит исключение, если не существует. saveOrUpdate можно юзать.
//        Employee employee1 = (Employee) session.merge(employee); //proxy
//        session.flush();

        transaction.commit();
        session.close();
        return "OK";
    }

    public String remove() {
        Employee employee = new Employee(0, "test", 1, new ArrayList<>());
        employee.getTasks().add(new EmployeeTask(0, "test1", LocalDate.now()));
        employee.getTasks().add(new EmployeeTask(0, "test2", LocalDate.now()));
        EmployeeTask task = new EmployeeTask(0, "test2", LocalDate.now());
        employee.getTasks().add(task);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        session.flush();
        session.clear();
        transaction.commit();
        transaction = session.beginTransaction();
        employee = session.find(Employee.class, employee.getId());
        employee.getTasks().remove(2);
        session.flush();
        transaction.commit();
        transaction = session.beginTransaction();
        employee = session.find(Employee.class, employee.getId());
        session.remove(employee);
        //or
//        session.createQuery("DELETE FROM Employee WHERE id = :id")
//                .setParameter("id", employee.getId())
//                .executeUpdate();
        session.flush();
        transaction.commit();
        session.close();
        return "OK";
    }

    public List<Test> softRemove() {
        Test test = new Test(0, "test", true, 0, 0);
        Test test1 = new Test(0, "test1", false, 0, 0);
        Test test2 = new Test(0, "test2", true, 0, 0);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(test);
            session.persist(test1);
            session.persist(test2);
            session.flush();
            session.clear();
            transaction.commit();
            transaction = session.beginTransaction();
            test = session.find(Test.class, test.getId());
            test.softDeleted();
            session.flush();
            session.clear();
            transaction.commit();
            return session.createQuery("from Test").list();
        }
    }
}
