package org.ioopm.calculator.ast;

/**
 * An assignment node with two children
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 * @see Binary
 */
public class Assignment extends Binary{
    public static final String name = "=";



    /**
     * default constructor for Assignment class
     * @param lhs child on the left hand side
     * @param rhs child on the right hand side
     */
    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs){
	super(lhs, rhs);
    }

    /**
     * get the mathmatical expression this class represents
     * @return the equals sign
     */
    @Override
    public String getName(){
	return "=";
    }

    @Override
    public int getPriority(){
	return 4;
    }

    /**
     * gets the value of the expression from the left child
     * @return the value of the left child
     */
    @Override
    public double getValue(){
	return this.lhs.getValue();
    }

    /**
     * evaluates what the lhs maps to
     * @param vars an environment to store variables
     * @return the evaluated expression
     */
    @Override
    public SymbolicExpression eval(Environment vars){
	SymbolicExpression lhs = this.lhs.eval(vars);
	Constants constants = new Constants();
	if(constants.namedConstants.containsKey(rhs.toString())) {
	   throw new IllegalExpressionException("Not allowed to reassign '" + rhs.toString() + "' try another variable");
	} else {
	    vars.put((Variable)this.rhs, lhs);
	    if(lhs.isConstant())
		return new Constant(lhs.getValue());
	    else
		return new Assignment(lhs, rhs);
	}
    }
}
