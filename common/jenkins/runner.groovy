import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

def runServiceStep(service, command) {
    dir("services/$service") {
        sh "make $command"
    }
}

def runSharedStep(command) {
    sh "make $command"
}


def execute(step, services) {
  stage(step.name) {
    if (step.shared) {
        runSharedStep(step.command)
    } else {
        parallel services.collectEntries {service -> [service, {runServiceStep(service, step.command)}]}
    }
  }
}

return { services, pipeline -> pipeline.each {step -> execute(step, services) } }