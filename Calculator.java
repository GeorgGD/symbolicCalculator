package org.ioopm.calculator.parser;

import org.ioopm.calculator.parser.*;
import org.ioopm.calculator.ast.*;
import java.io.IOException;

public class Calculator {
    private static int fullyEvaluated;
    private static int evaluated;
    private static int expressions;
    
    
    public static void main(String[] args) {
	System.out.println("Welcome to the parser!");
	Environment vars = new Environment();
	CalculatorParser cp = new CalculatorParser("", vars);
	
	while(true) {
	    System.out.print("Please enter an expression: ");

	    String input = System.console().readLine();
	    try {
		if(0 == input.compareTo("quit")) {
		    System.out.println("Fully evaluated: " + fullyEvaluated);
		    System.out.println("Evaluations: " + evaluated);
		    System.out.println("Expressions entered: " + expressions);
		}
		SymbolicExpression result = cp.statement(input);
		expressions += 1;
		if (result != null) {
		    if(!result.isCommand())
			evaluated += 1;
		    if(result.eval(vars).isConstant())
			fullyEvaluated += 1;
		    System.out.println(result.eval(vars));
		    SymbolicExpression ans = new Assignment(result, new Variable("ans"));
		    ans.eval(vars);
		}	
	    } catch(IOException e) {
		System.err.println("IO Exception!");
	    } catch(SyntaxErrorException e) {
		System.err.println("Syntax error: " + e);
	    } catch(IllegalExpressionException e) {
		System.err.println("Syntax error: " + e);
	    }
	}
	
    }
}
