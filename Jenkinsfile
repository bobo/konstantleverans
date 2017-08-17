pipeline {
    agent any
    stages {
        stage("run maven test") {
            steps {
                    sh 'mvn clean install'
                }
        }
    }
}
