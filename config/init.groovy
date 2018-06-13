import jenkins.model.Jenkins;

Jenkins.instance.securityRealm.createAccount{"admin","password123"}

Jenkins.instance.setSlaveAgentPort(9999)
Jenkins.instance.save() 
