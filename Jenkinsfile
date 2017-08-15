pipeline {
    agent any
    stages {
        stage("run maven test") {
            steps {
                container(name: 'maven:alpine') {   
                   sh "mvn test"
                } 
            }
        }
    }
}
