package org.ioopm.calculator.ast;

/**
 * Addition node with two children
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Addition extends Binary {
    public static final String name = "+";



    /**
     * default constructor for Addition
     * @param lhs child on the left hand side
     * @param rhs child on the right hand side
     */
    public Addition(SymbolicExpression lhs, SymbolicExpression rhs){
	super(lhs, rhs);
    }

    /**
     * gets the mathmatical expression for addition
     * @return the plus sign
     */
    @Override
    public String getName(){
	return "+";
    }

    /**
     * gets the result for the addition of the lhs and rhs
     * @return the value after the addition
     */
    @Override
    public double getValue(){
	    return this.lhs.getValue() + this.rhs.getValue();
    }


    @Override
    public int getPriority(){
	return 2;
    }

    /**
     * checks if the lhs and rhs are constants
     * @return true of both sides are constants otherwise false
     */

    @Override
    public boolean isConstant(){
	return this.rhs.isConstant() && this.lhs.isConstant();
    }

    /**
     * evaluates the addition
     * @raram vars an environment to store variables
     * @returns a constant of the evaluated addition if lhs and rhs is constants else returns an addition of lhs and rhs
     */
    @Override
    public SymbolicExpression eval(Environment vars){
	SymbolicExpression lhs = this.lhs.eval(vars);
	SymbolicExpression rhs = this.rhs.eval(vars);
	if(lhs.isConstant() && rhs.isConstant()) {
	    return new Constant(lhs.getValue() + rhs.getValue());
	} else {
	    return new Addition(lhs, rhs);
	}
    }
}
