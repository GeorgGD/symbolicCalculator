package org.ioopm.calculator.ast;

/**
 * Turns a parsed number into a Constant node in the abstract syntax tree
 * Read the README file on how to setup and run the program
 * @author Gerogios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Constant extends Atom{
    private double value;

    /**
     * default constructor for a Constant
     * @param value the value to be converted to a Constant
     */
    public Constant(double value){
	this.value = value;
    }

    /**
     * Sets the isConstant() method to true
     * @return true
     */
    @Override
    public boolean isConstant(){
	return true;
    }

    @Override
    public double getValue(){
	return this.value;
    }

    @Override
    public String toString() {
	return String.valueOf(this.getValue());
    }

    /**
     * checks if this is equal to another object other
     * @return true if the Object other is of the same class as this and if the field value of other is the same as this.value
     */
    @Override
    public boolean equals(Object other) {
	if (other instanceof Constant) {
	    return this.equals((Constant) other);
	} else {
	    return false;
	}
    }

    public boolean equals(Constant other) {
	/// access a private field of other!
	return this.value == other.value;
    }

    /**
     * evaluates a constant inside an environment
     * @param vars an environment to store variables
     * @return a constant with the given value
     */
    @Override
    public SymbolicExpression eval(Environment vars){
	return new Constant(this.value);
    }
}
