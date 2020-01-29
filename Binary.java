package org.ioopm.calculator.ast;

public abstract class Binary extends SymbolicExpression{
    SymbolicExpression lhs;
    SymbolicExpression rhs;

    public Binary(SymbolicExpression lhs, SymbolicExpression rhs){
	this.lhs = lhs;
	this.rhs = rhs;
    }

    @Override
    public String toString(){
	if (this.getPriority() > lhs.getPriority() && this.getPriority() > rhs.getPriority())
	    return "(" + this.lhs + ")" + this.getName() + "(" + this.rhs + ")";
	if (this.getPriority() > lhs.getPriority())
	    return "(" + this.lhs + ")" + " " + this.getName() + this.rhs;
	if (this.getPriority() > rhs.getPriority())
	    return this.lhs + " " + this.getName() + "(" + this.rhs + ")";
	return this.lhs + " " + this.getName() + " " + this.rhs;
    }

    @Override
    public boolean isConstant(){
	return this.lhs.isConstant() && this.rhs.isConstant();
    }

    @Override
    public boolean equals(Object other) {
	if (other instanceof Binary) {
	    return this.equals((Binary) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Binary other) {
	/// access a private field of other!
	return this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }
}
