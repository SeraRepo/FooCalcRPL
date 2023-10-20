JFLAGS = -g -sourcepath . 
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	FooCalcRPL.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) UI/*.class
	$(RM) obj/*.class

run:
	java FooCalcRPL

rerun:
	make clean
	make
	make run