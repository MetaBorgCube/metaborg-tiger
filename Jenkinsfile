// Keep artifacts for last 3 builds, disable concurrent builds, build when /metaborg/spoofax-releng/master job builds.
properties([
  buildDiscarder(logRotator(artifactNumToKeepStr: '3'))
, disableConcurrentBuilds()
, pipelineTriggers([upstream(threshold: hudson.model.Result.SUCCESS, upstreamProjects: '/metaborg/spoofax-releng/master')])
])

node('spoofax-buildenv-jenkins') {
  def slackChannel = '#spoofax-dev'
  
  // In Jenkins, under Tools, add a JDK Installation with:
  // - Name: JDK 11
  // - JAVA_HOME: /usr/lib/jvm/java-11-openjdk-amd64
  // - Install automatically: false
  // Ensure the JDK 11 is available in the Spoofax Docker image at the specified path.
  jdk = tool name: 'JDK 11'
  env.JAVA_HOME = "${jdk}"

  try {
    
    stage('Echo') {
      // Print important variables and versions for debugging purposes.
      exec 'env'
      exec 'bash --version'
      exec 'git --version'
      exec 'python3 --version'
      exec 'pip3 --version'
      exec "$JAVA_HOME/bin/java -version"
      exec "$JAVA_HOME/bin/javac -version"
      exec 'mvn --version'
    }
    
    stage('Checkout') {
      checkout scm
      sh 'git clean -ddffxx'
    }

    stage('Build and Test') {
      withMaven(
        mavenLocalRepo: ".repository"
      , globalMavenSettingsFilePath: ".mvn/settings.xml"
      , mavenOpts: '-Xmx1G -Xms1G -Xss16m'
      ) {
        sh 'mvn -B -U -e clean -DforceContextQualifier=\$(date +%Y%m%d%H%M)'
        sh 'mvn -B -U -e install -DforceContextQualifier=\$(date +%Y%m%d%H%M)'
      }
    }

    stage('Archive') {
      archiveArtifacts(
        artifacts: 'org.metaborg.lang.tiger.eclipse.site/target/site/'
      , excludes: null
      , onlyIfSuccessful: true
      )
    }

    stage('Cleanup') {
      sh "git clean -ddffxx"
    }
  } catch(org.jenkinsci.plugins.workflow.steps.FlowInterruptedException e) {
    if(e.causes.size() == 0) {
      throw e // No causes, signals abort.
    } else {
      notifyFail(slackChannel)
      throw e
    }
  } catch(hudson.AbortException e) {
    if(e.getMessage().contains('script returned exit code 143')) {
      throw e // Exit code 143 means SIGTERM (process was killed), signals abort.
    } else {
      notifyFail(slackChannel)
      throw e
    }
  } catch(e) {
    notifyFail(slackChannel)
    throw e
  }
  notifySuccess(slackChannel)
}

def createMessage(String message) {
  return "${env.JOB_NAME} - ${env.BUILD_NUMBER} - ${message} (<${env.BUILD_URL}|Status> <${env.BUILD_URL}console|Console>)"
}
def notifyFail(String channel) {
  if(!channel) {
    return
  }

  def prevBuild = currentBuild.getPreviousBuild()
  if(prevBuild) {
    if('SUCCESS'.equals(prevBuild.getResult())) {
      slackSend channel: channel, color: 'danger', message: createMessage('failed')
    } else {
      slackSend channel: channel, color: 'danger', message: createMessage('still failing')
    }
  } else {
    slackSend channel: channel, color: 'danger', message: createMessage('failed')
  }
}
def notifySuccess(String channel) {
  if(!channel) {
    return
  }

  def prevBuild = currentBuild.getPreviousBuild()
  if(prevBuild && !'SUCCESS'.equals(prevBuild.getResult())) {
    slackSend channel: channel, color: 'good', message: createMessage('fixed')
  }
}
