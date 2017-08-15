pipeline {
    agent none

    stages {
        stage("run maven test") {
            agent { docker 'maven:3-alpine' }
            steps {
               sh "mvn test"
            }   
        }
    }
}
