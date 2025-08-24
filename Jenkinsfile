pipeline {
    agent any

    environment {
        PATH = "/usr/local/bin:/Users/keertichandanthatavarthi/Softwares/apache-maven-3.9.11/bin:${env.PATH}"
        TOMCAT_HOME = "/Users/keertichandanthatavarthi/Softwares/apache-tomcat-10.1.43"
    }

    stages {

        // ===== FRONTEND BUILD =====
        stage('Build Frontend') {
            steps {
                dir('UserF') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        // ===== FRONTEND DEPLOY =====
        stage('Deploy Frontend to Tomcat') {
            steps {
                sh '''
                FRONTEND_PATH="$TOMCAT_HOME/webapps/reactuserapi"

                # Remove old frontend
                rm -rf "$FRONTEND_PATH"

                # Create new folder and copy dist files
                mkdir -p "$FRONTEND_PATH"
                cp -R UserF/dist/* "$FRONTEND_PATH/"
                '''
            }
        }

        // ===== BACKEND BUILD =====
        stage('Build Backend') {
            steps {
                dir('SemInlab') {
                    sh 'mvn clean package'
                }
            }
        }

        // ===== BACKEND DEPLOY =====
        stage('Deploy Backend to Tomcat') {
            steps {
                sh '''
                WEBAPPS_PATH="$TOMCAT_HOME/webapps"

                # Remove old WAR and exploded folder
                rm -f "$WEBAPPS_PATH/springbootuserapi.war"
                rm -rf "$WEBAPPS_PATH/springbootuserapi"

                # Copy new WAR
                cp SemInlab/target/*.war "$WEBAPPS_PATH/"
                '''
            }
        }

        // ===== RESTART TOMCAT =====
        stage('Restart Tomcat') {
            steps {
                sh '''
                $TOMCAT_HOME/bin/shutdown.sh || true
                sleep 3
                $TOMCAT_HOME/bin/startup.sh
                '''
            }
        }
    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Pipeline Failed.'
        }
    }
}
