package com.example.jsondata.tools;

import org.apache.log4j.Logger;

public class Log {
	private static final Logger logger = Logger.getLogger("com.example.jsondata");

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void debug(String msg) {
		logger.debug(msg);
	}
	
	public static void error(String msg) {
		logger.error(msg);
	}
	
	public static void warn(String msg){
		logger.warn(msg);
	}

}
