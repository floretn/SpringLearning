package ru.mephi.boot.dao.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS", schema = "USERS")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "USER_PK")
    private long id;

    @Column(name = "USER_MIDDLE_NAME")
    private String middleName;

    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @Column(name = "USER_EMAIL", unique = true)
    private String email;

    @Column(name = "USER_PHONE", unique = true)
    private String phone;

    @Column(name = "USER_USERNAME")
    private String username;

    @Column(name = "USER_PASSWORD_HASH")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE", schema = "USERS")
    private Set<RoleEntity> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
