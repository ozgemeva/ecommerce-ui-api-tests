pipeline {
  agent any
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Sanity')   { steps { sh 'echo Hello from Jenkins && ls -la' } }
  }
}
