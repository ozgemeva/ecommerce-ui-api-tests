pipeline {
  agent any

  tools { jdk 'Java21'; maven 'Maven3' }

  options {
    timestamps()
    ansiColor('xterm')
    buildDiscarder(logRotator(numToKeepStr: '20'))
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build') {
      steps {
        sh 'mvn -q -U -B clean install -DskipTests'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn -q -B test'
      }
      post {
        always {
          // JUnit format report(Surefire)
          junit '**/target/surefire-reports/*.xml'
          // Optinal: output/additional arşivle
          archiveArtifacts artifacts: 'target/**/*.(log|html|png|json)', allowEmptyArchive: true
        }
      }
    }
  }
}
