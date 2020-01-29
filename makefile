all:
	javac -d classes  *.java

run:
	java -cp classes org.ioopm.calculator.parser.Calculator

test:
	java -cp classes org.ioopm.calculator.ast.Test

cal:
	java -cp classes org.ioopm.calculator.parser.CalculatorParser

parserTest:
	java -cp classes org.ioopm.calculator.parser.TestParser

doc:
	javadoc -private *.java

readDoc:
	xdg-open documentation/index.html
