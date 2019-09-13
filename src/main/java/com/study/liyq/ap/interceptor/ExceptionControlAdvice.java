package com.study.liyq.ap.interceptor;

import com.study.liyq.ap.counter.CounterService;
import com.study.liyq.ap.exception.ApException;
import com.study.liyq.ap.exception.ErrorEnum;
import com.study.liyq.ap.model.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControlAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionControlAdvice.class);

    @Autowired
    private CounterService counterService;

    /**
     * Catch Exception
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ProductResponse exceptionHandler(Exception ex) {
        logger.error("Catch exception from controller", ex);
        counterService.incrAndGetFailCounter(1);
        ProductResponse response = new ProductResponse();
        response.success(false)
                .errorCode(ErrorEnum.INTERNAL_ERROR.getErrorCode())
                .errorMessage(ErrorEnum.INTERNAL_ERROR.getErrorMsg());
        return response;
    }

    @ResponseBody
    @ExceptionHandler(value = ApException.class)
    public ProductResponse apExceptionHandler(ApException ex) {
        logger.error("Catch ApException from controller", ex);
        counterService.incrAndGetFailCounter(1);
        ProductResponse response = new ProductResponse();
        response.success(false)
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getErrorMsg());
        return response;
    }

}
