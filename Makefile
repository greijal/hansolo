build:
	mvn install

run: build
	mvn install spring-boot:run -f api/pom.xml

