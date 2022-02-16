import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

def execute(step, services) {
  stage(step.name) {
    if (step.common) {
        echo "Running make ${step.command}"
    } else {
        parallel services.collectEntries {[it, {echo "Running make ${step.command}"}]}
    }
  }
}

return { services, pipeline -> pipeline.each {step -> execute(step, services) } }