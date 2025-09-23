pipeline {
  agent any
  tools { jdk 'jdk-1.8'; maven 'maven-3.9' }
  stages {
    stage('Checkout'){ steps { checkout scm } }
    stage('Build & Test'){ steps { sh './mvnw -B verify' } }
  }
  post {
    always {
      junit '**/target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
  }
}
