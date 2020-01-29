package org.ioopm.calculator.ast;

public abstract class SymbolicExpression {
    public static final String name = null;
    private SymbolicExpression lhs;
    private SymbolicExpression rhs;


    public boolean isConstant(){
	return false;
    }

    public String getName(){
	throw new RuntimeException("getName() called on expression with no operator");
    }

    public int getPriority(){
	return 10;
    }

    public double getValue(){
	throw new RuntimeException("getValue() called on expression with no value");
    }

    public boolean isCommand(){
	return false;
    }

    public abstract SymbolicExpression eval(Environment vars);

}
