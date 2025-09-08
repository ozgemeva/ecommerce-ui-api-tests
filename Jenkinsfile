pipeline {
  agent any

  tools { jdk 'Java21'; maven 'Maven3' }

  options {
    timestamps()
    buildDiscarder(logRotator(numToKeepStr: '20'))
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build') {
      steps {
        ansiColor('xterm') {
          sh 'mvn -q -U -B clean install -DskipTests'
        }
      }
    }

    stage('Test') {
      steps {
        ansiColor('xterm') {
          sh 'mvn -q -B test'
        }
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
          archiveArtifacts artifacts: 'target/**/*.log, target/**/*.html, target/**/*.png, target/**/*.json', allowEmptyArchive: true
        }
      }
    }
  }
}
