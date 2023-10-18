package test.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import test.dao.entity.Employee;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class SessionFactoryTestService {
    private final SessionFactory sessionFactory;
    public SessionFactoryTestService(EntityManagerFactory factory) {
//        Properties properties = new Properties();
//        properties.put(Environment.DRIVER, "com.postgresql.jdbc.Driver");
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
//        sessionFactory = new Configuration().addProperties(properties).buildSessionFactory();

        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public List<Employee> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.list();
        }
    }

    public Employee getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    public void addEmployee(List<Employee> employees) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            for (int i = 0; i < employees.size(); i++) {
                if (i == 2) {
                    throw new RuntimeException();
                }
                session.save(employees.get(i));
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public String testTransaction() {
        Employee employee = new Employee(0, "TestUser", 0);
        Employee employee1 = new Employee(0, "TestUser1", 1);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            session.persist(employee1);
            session.remove(employee);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return ex.getMessage();
        }
        return "OK";
    }
}
