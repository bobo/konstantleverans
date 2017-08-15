pipeline {
    agent {       
        docker {
            image 'maven:3-alpine'
            label 'kubernetes-agent'
        }
    }

    stages {
        stage("run maven test") {
          sh "mvn test"
        }

    }
}
