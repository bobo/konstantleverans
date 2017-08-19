
  node('kubernetes-agent') {
    stage('Build a Maven project') {
      container('maven') {
          sh 'echo "hello"'
      }
    }
}