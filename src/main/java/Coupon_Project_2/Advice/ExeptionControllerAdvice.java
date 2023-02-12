package Coupon_Project_2.Advice;

import Coupon_Project_2.SystemExeption.MyCouponSystemExeption;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

@RestControllerAdvice
public class ExeptionControllerAdvice {

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AdviceInfo handleSql(SQLException e){
        return new AdviceInfo("Error SQL", e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AdviceInfo handlerNotFound() {
        return new AdviceInfo(HttpStatus.NOT_FOUND.name(), "Page not found!");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AdviceInfo handlerMissingMethod(MethodArgumentTypeMismatchException e) {
        System.out.println(e);
        e.getStackTrace();
        return new AdviceInfo(e.getName(), e.getMessage());
    }

    @ExceptionHandler(MyCouponSystemExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)//////
    public AdviceInfo handlerInternal(MyCouponSystemExeption e) {
        return new AdviceInfo("coupon exception", e.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AdviceInfo handlerMissingHeader(MissingRequestHeaderException e) {
        System.out.println(e);
        e.getStackTrace();
        return new AdviceInfo(e.getHeaderName(), e.getMessage());
    }


}
