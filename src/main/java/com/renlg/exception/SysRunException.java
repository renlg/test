package com.renlg.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 自定义的运行时异常类
 */
public class SysRunException extends RuntimeException {
	private static final long serialVersionUID = -3395086485903492121L;

	private static Log logger = LogFactory.getLog(SysRunException.class);
	
	public SysRunException() {
		super();
	}
	
	/** 
	 * (常用)自定义异常：一般用此异常传递一些信息
	 * <p>	日志：logger.debug(message);
	 */
	public SysRunException(String message) {
		super(message);
		logger.debug(message);
	}
	
	/** 
	 * (常用)自定义异常：一般用此异常传递一些信息
	 * <p>	日志：logger.debug(message);
	 * @param addToError		为true时将日志加入到error级别中
	 */
	public SysRunException(String message, boolean addToError) {
		super(message);
		
		if (addToError) {
			logger.error(message);
		} else {
			logger.debug(message);
		}
	}

	/** 
	 * 自定义异常：传递异常信息及异常对象
	 * <p>	日志：logger.error(message, cause);
	 */
	public SysRunException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message, cause);
	}

	/** 
	 * 自定义异常：直接传递异常对象
	 * <p>	日志：logger.error(cause);
	 */
	public SysRunException(Throwable cause) {
		super(cause);
		logger.error(cause);
	}
}
