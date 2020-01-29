package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * A hash table that maps mathmatical constants to their values
 * Example: pi maps to 3.141592654
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Constants {
    public static final HashMap<String, Double> namedConstants = new HashMap<>();

    static {
	Constants.namedConstants.put("pi", Math.PI);
	Constants.namedConstants.put("e", Math.E);
	Constants.namedConstants.put("Answer", 42.0);
	Constants.namedConstants.put("L", 6.022140857 * (Math.pow(10,23)));
    }
}
