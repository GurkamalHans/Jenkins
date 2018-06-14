import jenkins.model.*
import hudson.util.*;
import jenkins.install.*;

def instance = Jenkins.getInstance()

instance.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)
instance.securityRealm.createAccount("admin","password123")

final List<String> REQUIRED_PLUGINS = [
    "aws-credentials",
    "copyartifact",
    "git",
    "ssh-agent",
    "tap",
    "workflow-aggregator",
]
if (instance.pluginManager.plugins.collect {
        it.shortName
    }.intersect(REQUIRED_PLUGINS).size() != REQUIRED_PLUGINS.size()) {
    REQUIRED_PLUGINS.collect {
         instance.updateCenter.getPlugin(it).deploy(true)
    }.each {
        it.get()
    }
    instance.restart()
    println 'Run this script again after restarting to create the jobs!'
}


instance.setSlaveAgentPort(9999)
instance.save() 
