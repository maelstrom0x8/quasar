package com.aeflheim.quasar.service;

import org.springframework.security.core.AuthenticationException;

public class EmailNotFoundException extends AuthenticationException {

	/**
	 * Constructs a <code>EmailNotFoundException</code> with the specified message.
	 * @param msg the detail message.
	 */
    public EmailNotFoundException(String message) {
        super(message);
    }

    /**
	 * Constructs a {@code EmailNotFoundException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public EmailNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
