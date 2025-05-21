pipeline {
  agent any
  triggers {
        pollSCM '* * * * *'
    }
  stages {
    stage('version') {
      steps {
        sh 'python3 --version'
      }
    }
    stage('hello Devops') {
      steps {
        sh 'python3 helloworld.py'
      }
    }
  }
}
