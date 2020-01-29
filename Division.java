package org.ioopm.calculator.ast;

/**
 * Division node with two children
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Division extends Binary{
    public static final String name = "/";



    /**
     * default constructor for Division
     * @param lhs child on the left hand side
     * @param rhs child on the right hand side
     */
    public Division(SymbolicExpression lhs, SymbolicExpression rhs){
	super(lhs, rhs);
    }

    /**
     * the mathmatical expression of division
     * @return the division sign
     */
    @Override
    public String getName(){
	return "/";
    }

    /**
     * gets the result for the division of the lhs and rhs
     * @return the value after the division
     */
    @Override
    public double getValue(){
	return this.lhs.getValue() / this.rhs.getValue();
    }

    /**
     * checks if the lhs and rhs are constants
     * @return true of both sides are constants otherwise false
     */
    @Override
    public boolean isConstant(){
	return this.lhs.isConstant() && this.rhs.isConstant();
    }

    @Override
    public int getPriority(){
	return 6;
    }


    /**
     * evaluates the division
     * @raram vars an environment to store variables
     * @returns the evaluated division
     */
    @Override
    public SymbolicExpression eval(Environment vars) {
	SymbolicExpression lhs = this.lhs.eval(vars);
	SymbolicExpression rhs = this.rhs.eval(vars);
	if(lhs.isConstant() && rhs.isConstant())
	    return new Constant(lhs.getValue() / rhs.getValue());
	else
	    return new Division(lhs , rhs);
    }
}
