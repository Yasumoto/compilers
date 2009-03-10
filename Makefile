CC := javac
SOURCES := ./Triangle/Compiler2.java ./TAM/Interpreter.java
CLASSDIR := cls
CLASSPATH := .

all:
	$(CC) -classpath $(CLASSPATH) -d $(CLASSDIR) $(SOURCES) -Xlint:unchecked -Xlint:deprecation

clean:
	rm -f $(CLASSDIR)/*/*.class
