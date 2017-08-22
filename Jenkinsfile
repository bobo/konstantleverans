podTemplate(name: "'kubernetes-agent'") {
  node('kubernetes-agent') {
    stage('Build a Maven project') {
      container('maven') {
          checkout scm
          sh 'mvn clean verify'
      }
    }
    }
}