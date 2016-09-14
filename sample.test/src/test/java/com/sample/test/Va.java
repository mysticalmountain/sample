package com.sample.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.validator.FormatException;
import com.sample.user.model.dto.USC01001;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * Created by andongxu on 16-8-19.
 */
public class Va {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static void main(String [] args) {
        USC01001 usc01001 = new USC01001();
        usc01001.setUsername("aa");
        Va va = new Va();
        va.tmp(usc01001);
    }

    public <I> void tmp(I i) {
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<I>> constraintViolations = validator.validate(i);
        List<Map> errors = new ArrayList<Map>();
        for (ConstraintViolation<I> constraintViolation : constraintViolations) {
            Map<String, String> error = new HashMap<String, String>();
            error.put(String.valueOf(constraintViolation.getPropertyPath()), constraintViolation.getMessage());
            errors.add(error);
        }
        System.out.println("------------->" + errors);
    }
}
