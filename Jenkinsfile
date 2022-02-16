properties([pipelineTriggers([pollSCM('* * * * *')])])

node {
    checkout scm
    
    def analyzeChanges = load "$env.WORKSPACE/common/jenkins/analyzeChanges.groovy"
    servicesToRun = analyzeChanges()
    println(servicesToRun)


    stage('Build') {
        echo 'Building the service'
    }
    stage('Service Tests') {
        echo 'Running service tests'
    }
    stage('Deploy') {
        echo 'Deploying service'
    }
}