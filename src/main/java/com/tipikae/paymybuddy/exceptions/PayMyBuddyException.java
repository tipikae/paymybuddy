package com.tipikae.paymybuddy.exceptions;

/**
 * Global exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class PayMyBuddyException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	public PayMyBuddyException() {
        super();
    }

	/**
	 * {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
    public PayMyBuddyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
     */
    public PayMyBuddyException(final String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
     */
    public PayMyBuddyException(final Throwable cause) {
        super(cause);
    }
}
