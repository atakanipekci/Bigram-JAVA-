all: begin

begin: _mainTester.class

_mainTester.class: _mainTester.java
	javac -g _mainTester.java

clean:
	rm -f *.class
