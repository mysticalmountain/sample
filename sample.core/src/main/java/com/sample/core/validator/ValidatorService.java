package com.sample.core.validator;

import com.alibaba.druid.support.json.JSONUtils;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.core.exception.api.IExceptionManager;
import com.sample.core.model.dto.ReqDto;
import com.sample.core.model.dto.RspDto;
import com.sample.core.service.IProcessor;
import com.sample.core.service.ISampleService;
import com.sun.tools.internal.ws.wsdl.framework.ValidationException;
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
            throw new FormatException(ExceptionLevel.SLIGHT, "000000", JSONUtils.toJSONString(errors), null, null, null);
        }
        return null;
    }
}
