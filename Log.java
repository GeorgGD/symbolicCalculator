package org.ioopm.calculator.ast;

/**
 * Log node with one child
 * Read the README file on how to setup and run the program
 * @author Georgies Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class Log extends Unary {
    public static final String name = "log";


    public Log(SymbolicExpression argument){
	super(argument);
    }

    @Override
    public String getName(){
	return "log";
    }

    @Override
    public boolean isConstant(){
	return this.argument.isConstant();
    }

    @Override
    public int getPriority(){
	return 6;
    }


    @Override
    public SymbolicExpression eval(Environment vars){
	SymbolicExpression argument = this.argument.eval(vars);
	if(argument.isConstant())
	    return new Constant(Math.log(argument.getValue()));
	else
	    return new Log(argument);

    }

}
