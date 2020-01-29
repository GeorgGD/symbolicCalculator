package org.ioopm.calculator.ast;
import java.util.HashMap;

/**
 * A hash table that contains all variables and the expressions/values they map to
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Environment extends HashMap<Variable, SymbolicExpression> {}
