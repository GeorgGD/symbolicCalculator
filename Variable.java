package org.ioopm.calculator.ast;

public class Variable extends Atom {
    private String identifier;

    public Variable(String identifier){
	this.identifier = identifier;
    }

    @Override
    public String toString(){
	return this.identifier;
    }

    @Override
    public boolean equals(Object other){
	if(other instanceof Variable)
	    return this.equals((Variable) other);
	else
	    return false;
    }

    public boolean equals(Variable other){
	return this.identifier.equals(other.identifier);
    }

    @Override
    public int hashCode(){
	String string = identifier;
	return string.hashCode();
    }

    @Override
    public SymbolicExpression eval(Environment vars){
	if(vars.containsKey(this)) {
	    return vars.get(this);
	} else {
	    return new Variable(identifier);
	}
    }
}
