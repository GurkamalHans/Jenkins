*Creates User account
Jenkins.instance.securityRealm.createAccount{"admin","password123"}

*Enables JNLP and sets fixed port
Jenkins.instance.setSlaveAgentPort(9999)
Jenkins.instance.save() 
