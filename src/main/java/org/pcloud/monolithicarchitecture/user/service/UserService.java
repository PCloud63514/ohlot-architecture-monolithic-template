package org.pcloud.monolithicarchitecture.user.service;

import org.pcloud.monolithicarchitecture.user.dto.form.UserJoinForm;
import org.pcloud.monolithicarchitecture.user.dto.response.UserJoinResponse;

public interface UserService {
    UserJoinResponse joinUser(UserJoinForm form);
}
