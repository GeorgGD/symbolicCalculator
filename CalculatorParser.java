package org.ioopm.calculator.parser;

import org.ioopm.calculator.parser.*;
import org.ioopm.calculator.ast.*;
import java.util.Scanner;
import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

/**
 * A parser for a calculator
 * Read the README file on how to setup and run the program
 * @author Georgios Davakos, Sebastian Fällman
 * @since 18 Nov 2019
 */
public class CalculatorParser{
    private StreamTokenizer st;
    /**
     * @see Environment
     */
    private Environment vars;
    /**
     * @see Constants
     */
    private Constants konst;

    private String[] commands = { "vars", "clear", "quit" };
    private char[] expressionOps = { '+', '-' };
    private char[] termOps = { '*', '/' };
    private String[] unary = { "sin", "cos", "exp", "log", "-" };

    /**
     * the default constructor for CalculatorParser
     * @param string the string that is going to be parsed
     * @param vars an environment to store all variables
     */
    public CalculatorParser(String string, Environment vars) {
	this.st = null;
	this.vars = vars;
    }

    /**
     * starts the parser
     * @return the parsed expression
     * @exception IOException on input error
     * @exception SyntaxErrorException when user attemts to input more things after a command
     * @see IOException
     * @see SyntaxErrorException
     */
    public SymbolicExpression statement(String string) throws IOException {
	this.st = new StreamTokenizer(new StringReader(string));
        this.st.ordinaryChar('-');
	this.st.ordinaryChar('/');
        this.st.eolIsSignificant(true);
	this.vars = vars;
	this.konst = new Constants();

	SymbolicExpression result = this.command();

	if(result != null) {
	    this.st.nextToken();
	    if(this.st.ttype != this.st.TT_EOF) {
		throw new SyntaxErrorException(this.st.sval + " can't be used as variables");
	    }

	    return result.eval(vars);
	}

	return this.assignment();
    }

    /**
     * checks if user input was a command
     * @return the given command
     * @exception IOException on input error
     * @see IOException
     */
    private SymbolicExpression command() throws IOException {
	if (this.st.nextToken() == this.st.TT_WORD) {
	    if (this.st.sval.equals(Quit.name)) {
		return Quit.instance();
	    }

	    if (this.st.sval.equals(Vars.name)) {
		return Vars.instance();
	    }

	    if (this.st.sval.equals(Clear.name)) {
		return Clear.instance();
	    }
	}

	this.st.pushBack();
	return null;
    }

    /**
     * sets the expression on the left hand side of an equal sign to be equal some given variable
     * @return what variable the expression to the left of an equal sign is equal to
     * @exception IOException on input error
     * @see IOException
     */
    private SymbolicExpression assignment() throws IOException {
	SymbolicExpression result = this.expression();
	this.st.nextToken();

	while (String.valueOf((char)this.st.ttype).equals(Assignment.name)) {
	    this.st.nextToken();
	    result = new Assignment(result, this.identifier());
	    this.st.nextToken();
	}

	this.st.pushBack();
	return result;
    }


    /**
     * checks if the expression contains addition or subtraction to parse
     * @return the parsed additions or subtractions or both
     * @exception IOException on input error
     * @see IOException
     */
    private SymbolicExpression expression() throws IOException {
	SymbolicExpression result = this.term();
	this.st.nextToken();

        while(this.isExpressionOp((char)this.st.ttype)) {
	    String operation = String.valueOf((char)this.st.ttype);

	    if (operation.equals(Addition.name)) {
		result = new Addition(result, term());
	    }
	    if (operation.equals(Subtraction.name)) {
		result = new Subtraction(result, term());
	    }

	    this.st.nextToken();
	}

	this.st.pushBack();
	return result;
    }

    /**
     * checks if the expression contains multiplication or division to parse
     * @return the parsed multiplication or division or both
     * @exception IOException on input error
     * @see IOException
     */
    private SymbolicExpression term() throws IOException {
        SymbolicExpression prod = this.factor();
        st.nextToken();

	while (this.isTermOp((char)this.st.ttype)) {
	    String operation = String.valueOf((char)this.st.ttype);

	    if (operation.equals(Multiplication.name)) {
		prod = new Multiplication(prod, factor());
	    }
	    if (operation.equals(Division.name)) {
		prod = new Division(prod, factor());
	    }

	    this.st.nextToken();
        }
	st.pushBack();
	return prod;
    }

    /**
     * checks if the expression contains parentheses, unary expressions or if its a number and parses it
     * @return the parsed parenthesis, unary expression or number
     * @exception IOException on input error
     * @exception SyntaxErrorException when the user uses paranthesis without a closing paranthasis
     * @see IOException
     * @see SyntaxErrorException
     */
    private SymbolicExpression factor() throws IOException {

        if (this.st.nextToken() == '('){
	    SymbolicExpression result = assignment();

            if (this.st.nextToken() != ')') {
                throw new SyntaxErrorException("expected ')'");
            }

	    return result;
        }

	if (this.st.ttype == this.st.TT_NUMBER || konst.namedConstants.containsKey(this.st.sval)) {
            return this.number();
	}
	if (this.isUnary((char)this.st.ttype) || this.isUnary(this.st.sval)) {
	    return this.unary();
	}
	if(this.st.ttype == this.st.TT_WORD) {
	    return this.identifier();
	}

	throw new SyntaxErrorException("Unexpected token: " + (char)this.st.ttype);
    }

    /**
     * finds the correct unary expression to parse it as
     * @return the unary expression
     * @exception IOException on input error
     * @see IOException
     */
    private SymbolicExpression unary() throws IOException {
	String token =
	    this.st.ttype == this.st.TT_WORD ? this.st.sval : String.valueOf((char)this.st.ttype);

	if (token.equals(Negation.name)) {
	    return new Negation(this.factor());
	}
	if (token.equals(Exp.name)) {
	    return new Exp(this.factor());
	}
	if (token.equals(Log.name)) {
	    return new Log(this.factor());
	}
	if (token.equals(Sin.name)) {
	    return new Sin(this.factor());
	}
	if (token.equals(Cos.name)) {
	    return new Cos(this.factor());
	}

	throw new RuntimeException("Fatal: failed to parse unary operation");
    }

    /**
     * parses a number
     * @return a number
     * @exception IOException on input error
     * @exception SyntaxErrorException when the function is provided a word
     * @see IOException
     * @see SyntaxErrorException
     */
    private SymbolicExpression number() throws IOException {
        if (this.st.ttype == this.st.TT_NUMBER) {
	    double number = this.st.nval;
            SymbolicExpression result = new Constant(number);
	    return result;
        }
        if (konst.namedConstants.containsKey(this.st.sval))
	    {
		double num = konst.namedConstants.get(this.st.sval);
		return new Constant(num);
	    } else {
            throw new SyntaxErrorException("Expected number");
        }
    }

    /**
     * parses a word into a variable
     * @return a variable
     * @exception IOException on input error
     * @exception SyntaxErrorException when the user uses the words quit, vars or clear
     * @see IOException
     * @see SyntaxErrorException
     */
    private SymbolicExpression identifier() throws IOException {
	if (this.st.ttype != this.st.TT_WORD || this.isCommand(this.st.sval)) {
	    throw new SyntaxErrorException("\"quit, vars,clear or numbers can't be used as variables\"");
	}

	return new Variable(this.st.sval);
    }


    /**
     * checks if the given string is a unary expression
     * @param token name of a variable or unary expression
     * @return true if the given value was a unary, otherwise false
     * @see IOException
     */
    private boolean isUnary(String token) {
	if(token == null) {
	    return false;
	}

	for(String s : this.unary) {
	    if(token.equals(s)) {
		return true;
	    }
	}

	return false;
    }

    private boolean isUnary(char token) {
	for(String s : this.unary) {
	    if(String.valueOf(token).equals(s)) {
		return true;
	    }
	}

	return false;
    }

    private boolean isCommand(String token) {
	if(token == null) {
	    return false;
	}

	for(String s : this.commands) {
	    if(token.equals(s)) {
		return true;
	    }
	}

	return false;
    }

    private boolean isExpressionOp(char token) {
	for(char c : this.expressionOps) {
	    if(token == c) {
		return true;
	    }
	}

	return false;
    }

    private boolean isTermOp(char token) {
	for(char c : this.termOps) {
	    if(token == c) {
		return true;
	    }
	}

	return false;
    }

}
