package com.cafe.managerassignment.auth;

import com.cafe.managerassignment.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter( applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(ApplicationUserRole.MANAGER.getGrantedAuthorities(), passwordEncoder.encode("11112222"), "manager", true, true, true, true),
                new ApplicationUser(ApplicationUserRole.USER.getGrantedAuthorities(), passwordEncoder.encode("pass1111"), "arsen", true, true, true, true)
        );
   return applicationUsers; }
}
