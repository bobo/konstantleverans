pipeline {
    agent any
    stages {
        container(name: 'maven:alpine') {   
            stage("run maven test") {
                steps {
                   sh "mvn test"
                } 
            }
        }
    }
}
