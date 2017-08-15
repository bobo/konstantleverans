pipeline {
    agent any
    stages {
        stage("run maven test") {
            steps {
                container(name: 'maven') {   
                   sh "mvn test"
                } 
            }
        }
    }
}
