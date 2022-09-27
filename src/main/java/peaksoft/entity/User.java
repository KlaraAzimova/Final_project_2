package peaksoft.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "user_seq", strategy = SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 2)
    private Long id;
    @Column(unique = true)
    private String password;
    private String firstName;
    private String email;

//    @ManyToMany(targetEntity = Role.class, cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch = EAGER)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles;
//    @OneToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch = EAGER)
//    private Role role;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Role r : role) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
        return Collections.singletonList(role);

    }

    @Override
    public String getUsername() {
        return email;
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
