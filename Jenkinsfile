properties([pipelineTriggers([pollSCM('* * * * *')])])

node {
    checkout scm
    
    def analyzeChanges = load "$env.WORKSPACE/common/jenkins/analyzeChanges.groovy"

    def servicesToRun = analyzeChanges()
    
    println('=========')
    println("Running: $servicesToRun")

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