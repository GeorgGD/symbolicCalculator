package org.ioopm.calculator.ast;

public class Quit extends Command{
    public static final String name = "quit";
    private static final Quit theInstance = new Quit();

    private Quit() {}

    public static Quit instance(){
	return theInstance;
    }

    @Override
    public boolean isCommand(){
	return true;
    }

    @Override
    public SymbolicExpression eval(Environment vars){
	System.out.println("Bye");
	System.exit(0);
	return null;
    }
}
