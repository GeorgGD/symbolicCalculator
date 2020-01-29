package org.ioopm.calculator.ast;

/**
 * Cosin node with one child
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Cos extends Unary {
    public static final String name = "cos";


    /**
     * default constructor for Cos
     * @param argument the value to operate upon
     */
    public Cos(SymbolicExpression argument){
	super(argument);
    }

    @Override
    public String getName(){
	return "cos";
    }

    /**
     * checks if argument is a constant
     * @return true if the argument inside cosin is a constant otherwise false
     */
    @Override
    public boolean isConstant(){
	return this.argument.isConstant();
    }

    @Override
    public int getPriority(){
	return 6;
    }


    /**
     * evaluates cosin
     * @param vars an environment to store variables
     * @return the evaluated cosin with the given argument
     */
    @Override
    public SymbolicExpression eval(Environment vars){
	SymbolicExpression argument = this.argument.eval(vars);
	if(argument.isConstant()) {
	    return new Constant(Math.cos(argument.getValue()));
	} else {
	    return new Cos(argument);
	}
    }

}
