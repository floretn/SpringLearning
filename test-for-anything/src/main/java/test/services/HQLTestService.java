package test.services;


import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import test.dao.entity.Employee;
import test.dao.entity.EmployeeTask;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

@Service
public class HQLTestService {

    private final SessionFactory sessionFactory;
    public HQLTestService(EntityManagerFactory factory) {
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public List<Employee> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery("from Employee", Employee.class).list();
            return session.createNamedQuery("Employee_FindAllEmployes", Employee.class).list();
        }
    }

    public List<String> getUserNamesMore0() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select distinct e.name from Employee e where e.salary > 0";
            Query<String> query = session.createQuery(hql , String.class);
            return query.list();
        }
    }

    public Employee last() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            ScrollableResults scroll = query.scroll();
            scroll.last();
            System.out.println(Arrays.toString(scroll.get()));
            return (Employee) scroll.get()[0];
        }
    }

    public Employee getById(int id) {
        try (Session session = sessionFactory.openSession()) {
//            Query<Employee> query = session.createQuery("from Employee e where e.id = :id", Employee.class);
            Query<Employee> query = session.createNamedQuery("Employee_FindById", Employee.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    public int updateSalary() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int result = session.createQuery("update Employee set salary = salary + 1").executeUpdate();
            transaction.commit();
            return result;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(ex);
        }
    }

    public List<EmployeeTask> getEmployeeTaskForFirstUser() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from EmployeeTask where employee.id = 1", EmployeeTask.class).list();
        }
    }

    public List<Employee> getEmployeeOldTask() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select distinct employee from EmployeeTask where deadline < current_date", Employee.class).list();
        }
    }

    public int allTasksTo1() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            transaction.begin();
            Employee employee = getById(1);
            Query query = session.createQuery("update EmployeeTask set employee = :user where employee != :user");
            query.setParameter("user", employee);
            return query.executeUpdate();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (transaction != null) {
                transaction.commit();
            }
        }
    }

    public List<EmployeeTask> getETInEmployeeList() {
        try (Session session = sessionFactory.openSession()) {
            Query<EmployeeTask> query = session.createQuery("from EmployeeTask where employee.name IN (:name_list)", EmployeeTask.class);
            query.setParameterList("name_list", new String[]{"testName1", "testName2"});
            return query.list();
        }
    }

    public List<EmployeeTask> get2From2Task() {
        try (Session session = sessionFactory.openSession()) {
            Query<EmployeeTask> query = session.createQuery("from EmployeeTask order by employee.name asc", EmployeeTask.class);
            query.setFirstResult(2);
            query.setMaxResults(2);
            return query.list();
        }
    }

    public int cntER() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("select count(id) from EmployeeTask", Long.class);
            return query.uniqueResult().intValue();
        }
    }

    public int updateNameById(int id, String newName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNamedQuery("Employee_UpdateEmployeeName");
            query.setParameter("id", id);
            query.setParameter("newName", newName);
            return query.executeUpdate();
        } finally {
            if (transaction != null) {
                transaction.commit();
            }
        }
    }

    public void testTask() {
        Employee employee;
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee where id = 28", Employee.class);
            employee = query.uniqueResult();
            query = session.createQuery("from Employee where id = 28", Employee.class);
            Employee employee1 = query.uniqueResult();
            System.out.println(employee1 == employee);
            employee = session.get(Employee.class, 28);
//            System.out.println(employee.getTasks().size());
//            employee.getTasks().get(1);
//            employee.getTasks().get(0);
//            employee.getTasks().get(2);
//            System.out.println(employee);
        }

        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee where id = 28", Employee.class);
            session.setCacheMode(CacheMode.IGNORE);
            Employee employee1 = session.get(Employee.class, 28);
//            Employee employee1 = query.uniqueResult();
            System.out.println(employee1 == employee);
//            System.out.println(employee.getTasks().size());
//            employee.getTasks().get(1);
//            employee.getTasks().get(0);
//            employee.getTasks().get(2);
//            System.out.println(employee);
        }
    }
}
