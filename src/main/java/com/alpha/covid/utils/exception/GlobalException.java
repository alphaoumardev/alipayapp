package com.alpha.covid.utils.exception;

import com.alpha.covid.utils.response.FinalResult;
import com.alpha.covid.utils.response.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//@Slf4j
@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(Exception.class)
    public FinalResult error(Exception e)
    {
        e.printStackTrace(); // this is the
        return FinalResult.error(ResultCode.ARITHMETIC);
    }

    @ExceptionHandler(ArithmeticException.class)
    public FinalResult error(HttpServletRequest request, ArithmeticException e)
    {
//        log.error("Global Exception [ArithmeticException]处理: [api: {}], 原因: {}", request.getRequestURL(), e.getMessage());
        return FinalResult.error(ResultCode.ARITHMETIC);
    }

    @ExceptionHandler(BusinessException.class)
    public FinalResult error(HttpServletRequest request, BusinessException e)
    {
//        log.error("全局异常[BusinessException]处理: [api: {}], 原因: {}", request.getRequestURL(), e.getErrorMessage());
        return FinalResult.error().code(e.getCode()).message(e.getErrorMessage());
    }
}
