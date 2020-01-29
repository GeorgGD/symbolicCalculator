package org.ioopm.calculator.ast;

public class Negation extends Unary {
    public static final String name = "-";


    /**
     * default constructor for Negation
     * @param argument the value to operate upon
     */
    public Negation(SymbolicExpression argument){
	super(argument);
    }

    @Override
    public String getName(){
	return "-";
    }


    @Override
    public int getPriority(){
	return 5;
    }

    /**
     * evaluates negation
     * @param vars en environment to store variables
     * @return the evaluated negation of the given value
     */
    @Override
    public SymbolicExpression eval(Environment vars){
	SymbolicExpression argument = this.argument.eval(vars);
	if(argument.isConstant())
	    return new Constant(-(argument.getValue()));
	else
	    return new Negation(argument);
    }
}
