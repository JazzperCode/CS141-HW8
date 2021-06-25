build: src/*.java
	javac src/*.java
	jar cfm 141OS.jar src/MANIFEST.MF src/*.class
