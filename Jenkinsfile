pipeline {
    agent any
    stages {
        stage("run maven test") {
            steps {
                kubernetes.pod('buildpod').withImage('maven').inside {
                    //for a single container you can avoid the .withNewContainer() thing.
                    git 'https://github.com/bobo/konstantleverans.git'
                    sh 'mvn clean install'
                }
            }
        }
    }
}
