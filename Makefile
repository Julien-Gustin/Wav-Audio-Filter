JFLAGS = -d bin -cp bin:audio.jar
JC =@ javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) src/*.java

CLASSES = \
        src/AdditionFilter.java \
				src/CompositeFilter.java \
				src/DelayFilter.java \
				src/Demo.java \
				src/GainFilter.java \
				src/GraphNode.java \
				src/Node.java \
				src/Queue.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) bin/*.class
