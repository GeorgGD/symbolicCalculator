package org.ioopm.calculator.ast;

public class Clear extends Command{
    public static final String name = "clear";
    private static final Clear theInstance = new Clear();

    private Clear(){}

    public static Clear instance(){
	return theInstance;
    }


    @Override
    public boolean isCommand(){
	return true;
    }

    @Override
    public SymbolicExpression eval(Environment vars){
	vars.clear();
	return null;
    }
}
