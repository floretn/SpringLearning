package ru.nspk.task9.dao.model.auth;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@Table(value = "user", schema = "auth")
public class User implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 2555411754320736844L;

    @Id
    @Column("id_usr")
    private Long id;

    @Column("login")
    @NotBlank(message = "Username can't be empty!")
    private String username;

    @Column("pwd")
    @NotBlank(message = "Password can't be empty!")
    private String password;

    @Override
    @SuppressWarnings("unchecked")
    public List<? extends GrantedAuthority> getAuthorities() {
        return (List<? extends GrantedAuthority>) Collections.EMPTY_LIST;
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
