#
# @(#)Makefile
#
# $Id: GNUmakefile,v 1.1.1.1 2001/11/03 05:39:14 huaz Exp $
#
# Created on October 29, 2000, 5:20 PM
#
# by Hua Zhong <huaz@cs.columbia.edu>
#

JAVA=java
JAVAC=javac

ALL: SharpTools.java
	$(JAVAC) SharpTools.java 

clean:
	rm -f *.class

test:
	$(JAVA) -classpath .:images.jar SharpTools $1 

rebuild:
	make clean
	make

jar:
	jar cvf SharpTools.jar *.class

run:
	$(JAVA) -classpath SharpTools.jar:images.jar:mm.mysql-2.0.4-bin.jar:hsqldb.jar SharpTools $1
