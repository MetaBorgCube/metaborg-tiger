// Keep artifacts for last 3 builds, disable concurrent builds, build when /metaborg/spoofax-releng/master job builds.
properties([
  buildDiscarder(logRotator(artifactNumToKeepStr: '3'))
, disableConcurrentBuilds()
, pipelineTriggers([upstream(threshold: hudson.model.Result.SUCCESS, upstreamProjects: '/metaborg/spoofax-releng/master')])
])

node {
  def slackChannel = '#spoofax-dev'

  try {
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
        sh 'mvn -B -U clean verify -DforceContextQualifier=\$(date +%Y%m%d%H%M)'
      }
    }

    stage('Archive') {
      archiveArtifacts(
        artifacts: 'org.metaborg.lang.tiger.eclipse.site/target/site'
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
