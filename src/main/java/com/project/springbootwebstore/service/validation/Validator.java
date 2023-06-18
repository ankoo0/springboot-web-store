package com.project.springbootwebstore.service.validation;

 interface Validator <T> {

    boolean validate(T t);
}
