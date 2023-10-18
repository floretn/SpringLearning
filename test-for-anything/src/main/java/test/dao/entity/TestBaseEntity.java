package test.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@MappedSuperclass
@ToString
@Getter
@Setter
@EntityListeners(value = TestBaseEntity.TimeEntityListener.class)
public class TestBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    protected int id;

    @Column(name = "test")
    protected String test;

    public static class TimeEntityListener {



        @PrePersist
        public void prePersist(Object o) {

        }

        public void met(List<Number> list) {

        }

        @PreUpdate
        public void preUpdate(Object o) {
            List<Number> list = new ArrayList<>();
            list.add(1);
            list.add((int) 2.2);
            met(list);
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
