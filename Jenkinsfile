pipeline {
    agent any
    stages {
        stage('Clean Workspace and Clone Repository') {
            steps {
                echo 'Cleaning up the workspace and cloning the repository...'
                script {
                    // Ensure the workspace is clean
                    if (fileExists('java_project')) {
                        echo 'Deleting the existing java_project directory...'
                        bat 'rmdir /s /q java_project'
                    } else {
                        echo 'No existing directory to delete.'
                    }
                }
                echo 'Cloning the repository...'
                bat 'git clone https://github.com/Cherry9676/java_project.git'
            }
        }
        
        stage('Clean the Project') {
            steps {
                echo 'Cleaning the Maven project...'
                bat 'mvn clean -f java_project/pom.xml'
            }
        }

        stage('Install Dependencies') {
            steps {
                echo 'Installing dependencies for the project...'
                bat 'mvn install -f java_project/pom.xml'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running the test scripts...'
                bat 'mvn test -f java_project/pom.xml'
            }
        }
    }
    post {
        always {
            echo 'Pipeline execution completed. Cleaning up workspace...'
            cleanWs()
        }
    }
}
