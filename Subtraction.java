package org.ioopm.calculator.ast;

/**
 * Subtraction node with two children
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Subtraction extends Binary {
    public static final String name = "-";



    /**
     * default constructor for Subtraction
     * @param lhs child on the left hand side
     * @param rhs child on the right hand side
     */
    public Subtraction(SymbolicExpression lhs, SymbolicExpression rhs){
	super(lhs, rhs);
    }

    /**
     * the mathmatical expression of subtraction
     * @return the minus sign
     */
    @Override
    public String getName(){
	return "-";
    }


    /**
     * gets the result for the subtraction of the lhs and rhs
     * @return the value after the subtraction
     */
    @Override
    public double getValue(){
	return this.lhs.getValue() - this.rhs.getValue();
    }


    /**
     * checks if the lhs and rhs are constants
     * @return true of both sides are constants otherwise false
     */
    @Override
    public boolean isConstant(){
	return this.rhs.isConstant() && this.lhs.isConstant();
    }

    @Override
    public int getPriority(){
	return 2;
    }

    /**
     * evaluates the subtraction
     * @raram vars an environment to store variables
     * @returns the evaluated subtraction
     */
    @Override
    public SymbolicExpression eval(Environment vars){
	SymbolicExpression lhs = this.lhs.eval(vars);
	SymbolicExpression rhs = this.rhs.eval(vars);
	if(lhs.isConstant() && rhs.isConstant())
	    return new Constant(lhs.getValue() - rhs.getValue());
	else
	    return new Subtraction(lhs, rhs);

    }

}
