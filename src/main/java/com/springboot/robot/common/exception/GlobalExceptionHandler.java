package com.springboot.robot.common.exception;

import com.springboot.robot.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 创 建 人：Connor
 * 创建时间：2017/10/25 16:34
 * 说    明：全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 打印异常栈
	 * @param e
	 */
	private void printStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		logger.error(sw.toString());
	}

	/**
	 * 方法参数校验异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ConstraintViolationException.class})
	public Object handleException(ConstraintViolationException e) {
		printStackTrace(e);
		if (ErrorMapping.getConstraintCode(ConstraintViolationException.class) < 5000) {
			return Result.failAtClient(getErrorMessage(e));
		}
		return Result.failAtServer(getErrorMessage(e));
	}


//	/**
//	 * 没有授权
//	 * @param e
//	 * @return
//	 */
//	@ExceptionHandler({org.apache.shiro.authz.UnauthorizedException.class})
//	public Object handleException(org.apache.shiro.authz.UnauthorizedException e) {
//		printStackTrace(e);
//		if (ErrorMapping.getCode(UnauthorizedException.class) < 5000) {
//			return Result.failAtClient(e.getMessage());
//		}
//		return Result.failAtServer(e.getMessage());
//	}


	/**
	 * 已知异常抓取
	 * @param e
	 * @return
	 */
	@ExceptionHandler({SystemException.class})
	public Object handleException(SystemException e) {
		printStackTrace(e);
		if (e.getCode() < 5000) {
			return Result.failAtClient(e.getMessage());
		}
		return Result.failAtServer(e.getMessage());
	}


	/**
	 * 未知异常抓取
	 * @param e
	 * @return
	 */
	@ExceptionHandler({Exception.class})
	public Object handleException(Exception e) {
		printStackTrace(e);
		return Result.failAtServer(e.getMessage());
	}

	/**
	 * 错误消息提取
	 */
	private String getErrorMessage(ConstraintViolationException e){
		StringBuffer errorMsg = new StringBuffer();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			// 获得验证失败的类 constraintViolation.getLeafBean()
			// 获得验证失败的值 constraintViolation.getInvalidValue()
			// 获取参数值 constraintViolation.getExecutableParameters()
			// 获得返回值 constraintViolation.getExecutableReturnValue()
//			errorMsg.append(constraintViolation.getLeafBean().getClass().getName());
//			errorMsg.append("-");
			errorMsg.append(constraintViolation.getMessage()+ "    ");
//			errorMsg.append(constraintViolation.getInvalidValue());
		}
		return errorMsg.toString();
	}

}
