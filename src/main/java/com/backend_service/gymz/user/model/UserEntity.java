package com.backend_service.gymz.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend_service.gymz.common.model.AbstractEntity;
import com.backend_service.gymz.user.common.enums.user.Gender;
import com.backend_service.gymz.user.common.enums.user.UserStatus;
import com.backend_service.gymz.user.common.enums.user.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Entity
@Slf4j
@Table(name = "tbl_user")
public class UserEntity extends AbstractEntity<Long> implements UserDetails {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<AddressEntity> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserHasRole> userRoles = new HashSet<>();

    public void saveAddress(AddressEntity address) {
        if (address != null) {
            if (addresses == null) {
                addresses = new ArrayList<>();
            }
            addresses.add(address);
            address.setUser(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserHasRole userHasRole : userRoles) {
            Role role = userHasRole.getRole();
            // Role-based authority
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

            // Permission-based authority
            for (RoleHasPermission roleHasPermission : role.getRolePermissions()) {
                Permission permission = roleHasPermission.getPermission();
                authorities.add(new SimpleGrantedAuthority(permission.getAuthority()));
            }
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.ACTIVE.equals(status);
    }

}
