package com.ppf.frame.common;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ControllerAdviceCommon {

    /**
     * 自定义异常处理
     * @param ex
     * @return
     */

    @ExceptionHandler(BindException.class)
        public AjaxResult errorHandler(BindException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(500);
        ajaxResult.setMessage(message);
        return ajaxResult;
    }
	@ExceptionHandler(UnknownAccountException.class)
	public AjaxResult shiroErrorHandler(UnknownAccountException ex) {
		String message = ex.getMessage();
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setCode(500);
		ajaxResult.setMessage(message);
		return ajaxResult;
	}

	@ExceptionHandler(IncorrectCredentialsException.class)
	public AjaxResult passwordErrorHandler(IncorrectCredentialsException ex) {
		String message = ex.getMessage();
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setCode(500);
		ajaxResult.setMessage(message);
		return ajaxResult;
	}

}
