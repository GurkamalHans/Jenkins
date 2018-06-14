import jenkins.model.*
import hudson.util.*;
import jenkins.install.*;

Jenkins.instance.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)
Jenkins.instance.securityRealm.createAccount("admin","password123")

final List<String> REQUIRED_PLUGINS = [
    "aws-credentials",
    "copyartifact",
    "git",
    "ssh-agent",
    "tap",
    "workflow-aggregator",
]
if (Jenkins.instance.pluginManager.plugins.collect {
        it.shortName
    }.intersect(REQUIRED_PLUGINS).size() != REQUIRED_PLUGINS.size()) {
    REQUIRED_PLUGINS.collect {
         Jenkins.instance.updateCenter.getPlugin(it).deploy(true)
    }.each {
        it.get()
    }
    Jenkins.instance.restart()
    println 'Run this script again after restarting to create the jobs!'
}


Jenkins.instance.setSlaveAgentPort(9999)
Jenkins.instance.save() 
