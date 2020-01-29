package org.ioopm.calculator.ast;
import org.ioopm.calculator.ast.*;

public class Test{

    public static void testPrinting(String expected, SymbolicExpression e, String testType) {
	if (expected.equals("" + e)) {
	    System.out.println("Passed: " + e + "\tTest: " + testType);
	} else {
	    System.out.println("Error: expected '" + expected + "' but got '" + e + "'" + "\tTest: " + testType);
	}
    }

    public static void testEvaluating(SymbolicExpression expected, SymbolicExpression e, Environment vars) {
	SymbolicExpression r = e.eval(vars);
	if (r.equals(expected)) {
	    System.out.println("Passed: " + e);
	} else {
	    System.out.println("Error: expected '" + expected + "' but got '" + r + "'");
	}
    }

    public static void main(String[] args) {
	Constant c1 = new Constant(5);
	Constant c2 = new Constant(2);
	Constant c3 = new Constant(2);
	Constant c4 = new Constant(3);
	Constant c5 = new Constant(0);
	Constant c6 = new Constant(Math.PI/2);
	Constant c7 = new Constant(1);
	Variable v = new Variable("x");
	Variable v1 = new Variable("x");
	Addition a = new Addition(c1, v);
	Addition a1 = new Addition(c4, c2);
	Multiplication m = new Multiplication(a, c2);
	Multiplication m1 = new Multiplication(c1, c2);
	Division d = new Division(c1, c2);
	Division d1 = new Division(a, c2);
	Subtraction s = new Subtraction(c1, c2);
	Subtraction s1 = new Subtraction(a, c2);
	Exp exp = new Exp(c3);
	Exp exp1 = new Exp(m1);
	Exp exp2 = new Exp(s1);
	Cos cos = new Cos(c5);
	Sin sin = new Sin(c5);
	Cos cos1 = new Cos(c6);
	Sin sin1 = new Sin(c6);
	SymbolicExpression sym = new Assignment(new Constant(5), new Constant(37));
	SymbolicExpression sym1 = new Constant(42);
	Environment vars = new Environment();
	Environment varsx = new Environment();
	Assignment x = new Assignment(new Constant(8), v);
	//Vars var = new Vars();


	Test t1 = new Test();
	if(c2.equals(c3))
	    System.out.println("c2 constant is equal to c3 constant");
	if(c3.equals(c2))
	    System.out.println("c3 constant is equal to c2 constant");
	if((a1.toString()).equals(c1.toString()))
	    System.out.println("a1 addition is equal to c1 constant");

	System.out.println("\nTests for printing");
	t1.testPrinting("5.0", c1, "Constant 5.0");
	t1.testPrinting("2.0", c2, "Constant 2.0");
	t1.testPrinting("5.0+x", a, "Addition Constant 5.0 and 'x'");
	t1.testPrinting("3.0+2.0", a1, "Addition Constants 3.0 and 2.0");
	t1.testPrinting("5.0*2.0", m1, "Multiplikation 5.0 and 2.0");
	t1.testPrinting("5.0/2.0", d, "Division 5.0 and 2.0");
	t1.testPrinting("5.0-2.0", s, "Subtraction 5.0 and 2.0");
	t1.testPrinting("exp 2.0", exp, "Exponential of 2.0");
	t1.testPrinting("(5.0+x)*2.0", m, "Multiplikation (5.0+x) and 2.0");
	t1.testPrinting("(5.0+x)/2.0", d1,"Division (5.0+x) and 2.0");
	t1.testPrinting("5.0+x-2.0", s1, "Subtraction (5.0+x) and 2.0");
	t1.testPrinting("exp 5.0*2.0", exp1, "Exponential of m1");
	t1.testPrinting("exp 5.0+x-2.0", exp2, "Exponential of s1");

	System.out.println("\nTests for evals");
	t1.testEvaluating(c3, new Constant(2), vars);
	t1.testEvaluating(new Addition(new Constant(5), new Variable("x")), a, vars);
	t1.testEvaluating(c4, s, vars);
	t1.testEvaluating(c1, a1, vars);
	t1.testEvaluating(c5, sin, vars);
	t1.testEvaluating(c7, cos, vars);
	t1.testEvaluating(c7, sin1, vars);
	t1.testEvaluating(new Constant(Math.cos(c6.getValue())), cos1, vars);
	t1.testEvaluating(new Constant(2.5) , d, vars);
	t1.testEvaluating(c7 , new Exp(c5), vars);
	t1.testEvaluating(c7, new Log(new Constant(Math.E)), vars);
	t1.testEvaluating(c3, new Exp(new Log(c3)), vars);
	t1.testEvaluating(new Constant(21) , new Multiplication(new Addition(c1,c2), new Addition(c2,c7)), vars);
	//t1.testEvaluating(sym1, sym, "Test från hemsidan", vars);
	t1.testEvaluating(new Variable("x"), v, vars);
	t1.testEvaluating(new Constant(8), x, varsx);
	t1.testEvaluating(new Constant(26), m, varsx);
	t1.testEvaluating(new Sin(new Constant(10)).eval(vars), new Sin(new Multiplication(new Constant(2),new Constant(5))),vars);
	Vars.instance().eval(varsx);
    }
}
