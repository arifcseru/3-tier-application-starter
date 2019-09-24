package test.infy.loggertest;

import org.apache.log4j.Logger;

public class HelloExample {

	final static Logger logger = Logger.getLogger(HelloExample.class);

	public static void main(String[] args) {

		HelloExample obj = new HelloExample();
		obj.runMe("mkyong");

	}

	private void runMe(String parameter) {

		if (logger.isDebugEnabled()) {
			logger.debug("This is debug : " + parameter);
			System.out.println("logger creaed...");
		}

		if (logger.isInfoEnabled()) {
			logger.info("This is info : " + parameter);
		}

		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);

	}

}