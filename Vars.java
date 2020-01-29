package org.ioopm.calculator.ast;

public class Vars extends Command {
    public static final String name = "vars";
    private static final Vars theInstance = new Vars();

    private Vars() {}

    public static Vars instance(){
	return theInstance;
    }

    @Override
    public boolean isCommand(){
	return true;
    }

    @Override
    public SymbolicExpression eval(Environment vars){
	if(vars.isEmpty()) {
	    System.out.println("No variables declared");
	    return null;
	} else {
	    String string = "";
	    for(Variable var : vars.keySet()){
		string += var + " = " + vars.get(var) + "\n";
	    }
	    string = string.substring(0, string.length()-1);
	    System.out.println(string);
	    return null;
	}
    }
}
