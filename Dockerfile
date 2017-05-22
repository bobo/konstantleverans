FROM jeanblanchard/java:8
ADD target/konstantleverans*.jar konstantleverans.jar

CMD java -jar konstantleverans.jar