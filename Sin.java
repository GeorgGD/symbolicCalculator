package org.ioopm.calculator.ast;

/**
 * Sinus node with one child
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Sin extends Unary {
    public static final String name = "sin";


    /**
     * default constructor for Sin
     * @param argument the value to operate upon
     */
    public Sin(SymbolicExpression argument){
	super(argument);
    }

    @Override
    public String getName(){
	return "sin";
    }

    @Override
    public double getValue(){
	return this.argument.getValue();
    }

    @Override
    public boolean isConstant(){
	return this.argument.isConstant();
    }

    @Override
    public int getPriority(){
	return 6;
    }


    /**
     * evaluates sinus
     * @param vars en environment to store variables
     * @return the evaluated sinus with the given argument
     */
    @Override
    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression argument = this.argument.eval(vars);
	if (argument.isConstant()) {
	    return new Constant(Math.sin(argument.getValue()));
	} else {
	    return new Sin(argument);
	}
    }
}
