package ru.mephi.boot.dao.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLES", schema = "USERS")
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "ROLE_PK")
    private long id;

    @Column(name = "ROLE_SYMBOLIC_NAME")
    private String symbolicName;

    @Column(name = "ROLE_DESCRIPTION")
    private String desc;

    @Override
    public String getAuthority() {
        return symbolicName;
    }
}
