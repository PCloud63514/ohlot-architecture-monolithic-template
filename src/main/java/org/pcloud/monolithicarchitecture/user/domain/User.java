package org.pcloud.monolithicarchitecture.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private String id;
    private String password;
    private String name;
    private String email;

    public void changePassword(String password) {
        this.password = password;
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
