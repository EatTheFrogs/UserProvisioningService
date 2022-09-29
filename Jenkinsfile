pipeline {
    agent any
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage ('Test') {
            steps {
                sh 'mvn clean compile package'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                    if(env.BRANCH_NAME == 'main') {
                        archiveArtifacts artifacts: 'target/*.jar',
                                           onlyIfSuccessful: true
                    }
                }
            }
        }
    }
}