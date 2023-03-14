def SLACK_SEND_COLOR = '#ff0000'
pipeline {
  agent {
    label 'jdk11'
  }

  options {
    disableConcurrentBuilds()
    timeout(60)
  }

  environment {
    SONAR_AUTH_TOKEN = credentials('SONAR_AUTH_TOKEN')
  }

  stages {
    stage('Code Checkout') {
      steps {
        echo 'Checking Out'
        git branch: 'develop', credentialsId: 'github-cred', poll: false, url: 'https://github.com/maverick-poc/maverick-poc-android-app.git'
      }
    }
    stage('Unit Test') {
      steps {
        echo 'Testing..'
      }
    }
    stage('Build App') {
      steps {
        echo 'Building....'
        sh 'chmod +x gradlew'
        sh './gradlew assembleRelease'
      }
    }
    stage('SonarQube Test') {
      steps {
        echo 'Sonar....'
        sh ""
        "
        cat > sonar.sh << EOF\ nexport PATH = /app/sonar - scanner - 4.8 .0 .2856 - linux / bin: $PATH\ nsonar - scanner - Dproject.settings = sonarqube.properties - Dsonar.login = "${env.SONAR_AUTH_TOKEN}" - Dsonar.url = http: //pipeline.mavipoc.com:9090\nEOF
          ""
        "
        sh 'cat sonar.sh'
        sh 'chmod +x sonar.sh'
        sh './sonar.sh'
      }
    }
    stage('Dependency Check') {
      steps {
        echo 'Dependency Check....'
        dependencyCheck additionalArguments: '', odcInstallation: 'Dependency-Check'
        dependencyCheckPublisher pattern: 'dependency-check-report.xml'
      }
    }
    stage('Upload to AppCenter') {
      environment {
        APP_CENTER_TOKEN_ANDROID = credentials('APP_CENTER_TOKEN_ANDROID')
      }
      steps {
        echo 'Deploying....'
        appCenter apiToken: "${env.APP_CENTER_TOKEN_ANDROID}", appName: 'Maverick-Android', branchName: '', buildVersion: '', commitHash: '', distributionGroups: 'Android-App-Group', mandatoryUpdate: false, notifyTesters: true, ownerName: 'maverick-poc', pathToApp: 'app/build/outputs/apk/release/app-release.apk', pathToDebugSymbols: '', pathToReleaseNotes: '', releaseNotes: 'Enjoy New Version of this App.'
        script {
          SLACK_SEND_COLOR = '#66cdaa'
        }
      }
    }
  }
  post {
    always {
      echo 'I will always say Hello again!'
      slackSend color: "${SLACK_SEND_COLOR}", message: "Jenkins Job ${env.JOB_NAME} With Build Number ${env.BUILD_NUMBER} Completed with ${currentBuild.currentResult}. To view logs click (<${env.BUILD_URL}|Open>)"
    }
  }
}
