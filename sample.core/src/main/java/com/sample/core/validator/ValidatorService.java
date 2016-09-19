package com.sample.core.validator;

import com.alibaba.druid.support.json.JSONUtils;
import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * Created by andongxu on 16-8-18.
 */
@Component
public class ValidatorService<I, O> implements IValidatorService<I, O> {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Override
    public O service(I i) throws UnifiedException {
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<I>> constraintViolations = validator.validate(i);
        List<Map> errors = new ArrayList<Map>();
        for (ConstraintViolation<I> constraintViolation : constraintViolations) {
            Map<String, String> error = new HashMap<String, String>();
            error.put(String.valueOf(constraintViolation.getPropertyPath()), constraintViolation.getMessage());
            errors.add(error);
        }
        if (errors.size() > 0) {
            throw new FormatException(ExceptionLevel.SLIGHT, Constant.ERROR_CODE_DATA_FORMAT, errors.toString(), null, null, null);
        }
        return null;
    }
}
