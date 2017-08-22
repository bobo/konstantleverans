podTemplate(name: "'kubernetes-agent'") {
  node('kubernetes-agent') {
    stage('Build a Maven project') {
      container('maven') {
          sh 'mvn clean verify'
      }
    }
  }
}