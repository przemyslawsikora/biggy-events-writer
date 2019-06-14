pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Build Docker') {
            when {
                expression { BRANCH_NAME ==~ /(develop|master)/ }
            }
            steps {
                sh './gradlew dockerTag -Ddocker_address=$docker_address'
            }
        }
        stage('Publish to Nexus') {
            when {
                expression { BRANCH_NAME ==~ /(develop|master)/ }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus_admin', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh '''
                       docker login -u $NEXUS_USER -p $NEXUS_PASSWORD $docker_address
                       ./gradlew dockerTagsPush -Ddocker_address=$docker_address
                       docker logout $docker_address
                       '''
                }
            }
        }
    }
}