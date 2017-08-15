pipeline {
    agent 'kubernetes-agent'

    stages {
        stage("run maven test") {
            container(name: 'golang') {
                steps {
                   sh "mvn test"
                } 
            }
        }
    }
}
