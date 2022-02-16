// Analyze changes

def listServices() {
    sh(script: "ls -1 $WORKSPACE/services/", returnStdout: true).split()
}


def changedFilesSinceLastPass() {
    def files = []
    def lastSuccessfulBuildNumber = currentBuild.previousSuccessfulBuild?.number

  if (lastSuccessfulBuildNumber == null) {
    println "No successful builds in history. Building everything"
    return []
  }

  println "Fetching changed files since build: $lastSuccessfulBuildNumber"

  def build = currentBuild
  while(build.number > lastSuccessfulBuildNumber) {
    files += listFilesForBuild(build)
    build = build.getPreviousBuild()
  }
  return files.unique()
}


def shouldRunAll(files) {
  def hasChangesInCommon = files.any {it.matches('common/.*')}
  def hasChangesInRoot = files.any {!it.contains('/')}
  print "hasChangesInCommon: $hasChangesInCommon, hasChangesInRoot: $hasChangesInRoot, files.isEmpty(): ${files.isEmpty()}"
  return hasChangesInCommon || hasChangesInRoot || files.isEmpty()
}


def servicesToRun() {
    def allServices = listServices()
    def changedFiles = changedFilesSinceLastPass()

    return shouldRunAll(changedFiles) ? allServices :
        allServices.findAll{serviceFolder -> changedFiles.any {it.contains("services/$serviceFolder")}}
}


node {
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