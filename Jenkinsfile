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
                    steps {
                        if(env.BRANCH_NAME == 'main') {
                            archiveArtifacts artifacts: 'target/*.jar',
                                               onlyIfSuccessful: true
                        }
                    }
                }
            }
        }
        stage ('Archive if main branch') {
            when {
               branch 'main';
            }
            steps {
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }
}