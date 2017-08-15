pipeline {
    agent any

    docker.image('maven:alpine').inside {

        stage("run maven test") {
          sh "mvn test"
        }
    }
}
