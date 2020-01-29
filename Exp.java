package org.ioopm.calculator.ast;


/**
 * Turns the parsed Exp into a node in the abstract syntax tree
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Exp extends Unary {
    public static final String name = "exp";


    /**
     * default constructor for Exp
     * @param argument the value to operate upon
     */
    public Exp(SymbolicExpression argument){
	super(argument);
    }

    @Override
    public String getName(){
	return "exp";
    }

    /**
     * checks if argument is a constant
     * @return true if argument is a constant otherwise false
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
     * evaluates exp
     * @param vars an environment to store variables
     * @return the evaluated exp with given argument
     */
    @Override
    public SymbolicExpression eval(Environment vars){
	SymbolicExpression argument = this.argument.eval(vars);
	if(argument.isConstant())
	    return new Constant(Math.exp(argument.getValue()));
	else
	    return new Exp(argument);
    }

}
