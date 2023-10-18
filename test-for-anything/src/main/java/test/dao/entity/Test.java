package test.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test", schema = "test")
@Where(clause = "DELETED = 0")
@EntityListeners(value = Test.TimeEntityListener.class)
public class Test extends TestBaseEntity {

    @Column(name = "bool", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean bool;

    @Column(name = "deleted") // если значение в колонке DELETED == 0, то запись жива, если 1 - мертва
    private Integer deleted;

    @Transient
    private final String s = "jopa";

    @Formula(value = "id * 100")
    public int total;

    @Embedded
    private TestEmbedded testEmbedded;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date")
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date updated;

    public Test(int id, String test, Boolean bool, Integer deleted, int total) {
        super();
        super.id = id;
        super.test = test;
        this.bool = bool;
        this.deleted = deleted;
        this.total = total;
    }

    //геттеры и сеттеры

    public void softDeleted() {
        this.deleted = 1; //помечаем запись как мертвую
    }

    @Embeddable
    @Getter
    @Setter
    private static class TestEmbedded {
        @Column(name="a")
        private int a;
        @Column(name="b")
        private int b;
    }

    public static class TimeEntityListener {

        @PrePersist
        public void prePersist(Object o) {

        }

        @PreUpdate
        public void preUpdate(Object o) {

        }

        @PreRemove
        public void preRemove(Object o) {

        }

        @PostLoad
        public void postLoad(Object o) {

        }

        @PostRemove
        public void postRemove(Object o) {

        }

        @PostUpdate
        public void postUpdate(Object o) {

        }

        @PostPersist
        public void postPersist(Object o) {
            if (o instanceof Test) {
                Test baseEntity = (Test) o;
                System.out.println(baseEntity);
            }
        }
    }
}
