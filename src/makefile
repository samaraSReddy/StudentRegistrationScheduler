JAVAC=javac
sources = $(wildcard *.java)
CLASSES = $(sources:.java=.class)

all: classes jar append 


clean :
	rm -f *.class *.jar *.run

%.class : %.java
	$(JAVAC) $<

classes : $(CLASSES)



manifest :
	$(wildcard *.MF)

jar : $(classes)
	jar cvfm SamaraHttpServer.jar MAINIFEST.MF SamaraHttpServer.class ThreadNotifier.class ThreadListener.class ThreadServer.class


append : SamaraHttpServer.jar runner.sh 
	cat runner.sh SamaraHttpServer.jar > SamaraHttpServer.run && chmod +x SamaraHttpServer.run

	
      	
