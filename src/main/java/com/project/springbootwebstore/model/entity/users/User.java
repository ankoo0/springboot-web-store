package com.project.springbootwebstore.model.entity.users;


import java.util.UUID;

public abstract class User {
    private Long uuid;
    private String avatarPath;
//    private FullName fullName;
    private String password;
    private boolean isEnabled;
    private Role role;

}
