package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.util.Constraint;
import com.major.project.travel.json.Error;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HUY on 10/16/2018
 */
@ControllerAdvice
public class RestAdvice {
    private static final Logger logger = LoggerFactory.getLogger(RestAdvice.class);

    @Qualifier(value = "exceptionSource")
    @Autowired
    private MessageSource exceptionSource;

    @Autowired
    LocaleResolver localeResolver;

    private static final String CONSTRAINT_COMMON_ERROR_CODE = "210"; // 210 is duplicated SQL code


    /**
     * Exception handler for rest api.
     *
     * @param ex       Exception
     * @param response HttpServletResponse
     * @return ExceptionWrapper
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Error handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Error has occurred: ", ex);
        int code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

        if (ex instanceof RestException) {
            code = ((RestException) ex).getCode();
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (ex instanceof DataNotFoundException) {
            code = ((DataNotFoundException) ex).getCode();
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (ex instanceof DataIntegrityViolationException) {
            response.setStatus(HttpServletResponse.SC_OK);
            DataIntegrityViolationException dataIntegrityViolationException = (DataIntegrityViolationException) ex;
            if (dataIntegrityViolationException.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintException = (ConstraintViolationException) dataIntegrityViolationException.getCause();
                String constraintName = constraintException.getConstraintName();

                String errorMessage = Constraint.getConstraintErrorMessage(constraintName);
                System.out.println("Exception ----" + errorMessage);
                // Need to apply i18n to localize error message.
                // Also specify real data like : 'Trial A was duplicated' or 'Trial A is existing. Please choose other name'.
                String localizedMessage = exceptionSource.getMessage(errorMessage, null, localeResolver.resolveLocale(request));

                return new Error(CONSTRAINT_COMMON_ERROR_CODE, localizedMessage);

            } else {
                return new Error(String.valueOf(CONSTRAINT_COMMON_ERROR_CODE), dataIntegrityViolationException.getMessage()); // 210 is duplicated SQL code
            }
        } else {
            response.setStatus(code);
        }
        return new Error(ex, String.valueOf(code));
    }
}
