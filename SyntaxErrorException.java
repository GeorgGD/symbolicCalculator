package org.ioopm.calculator.parser;

import org.ioopm.calculator.parser.*;

/**
 * SyntaxErrorException when incorrect input is found
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
class SyntaxErrorException extends RuntimeException {
    /**
     * The default constructor for SyntaxErrorException
     */
    public SyntaxErrorException() {
        super();
    }

    /**
     * prints the given error message 
     * @param msg an error message
     */
    public SyntaxErrorException(String msg) {
        super(msg);
    }
}
