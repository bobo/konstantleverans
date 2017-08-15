pipeline {
    agent {
        label 'kubernetes-agent'
        docker {
            image 'maven:3-alpine'
        }
    }

    stages {
        stage("run maven test") {
          sh "mvn test"
        }

    }
}
