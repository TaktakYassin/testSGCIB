1) Cloner le projet avec git : 
	git clone https://github.com/TaktakYassin/testSGCIB.git
2) Builder le projet avec maven :  
	mvn clean install
3) Exécuter le projet avec la classe main : 
	run TestSGCIBApplication.java
4) Se connecter à l'UI de swagger et importer un fichier txt avec le service POST calculatePositions  : 
	http://localhost:8080/swagger-ui/index.html

Des exemples de fichiers de données se trouvent dans src/test/resources