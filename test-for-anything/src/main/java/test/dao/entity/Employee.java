package test.dao.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "Employee_FindById",
                query = "from Employee where id = :id"),
        @org.hibernate.annotations.NamedQuery(name = "Employee_FindAllEmployes",
                query = "from Employee"),
        @org.hibernate.annotations.NamedQuery(name = "Employee_UpdateEmployeeName",
                query = "update Employee set name = :newName where id = :id")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee", schema = "test")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private int salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.EXTRA)
    //id from task
    @OrderColumn(name = "id")
//    @JoinColumn(name="employee_id", referencedColumnName = "id")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<EmployeeTask> tasks;

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @PrePersist
    private void prePersist() {
        for (EmployeeTask t : tasks) {
            t.setEmployee(this);
        }
    }
}
