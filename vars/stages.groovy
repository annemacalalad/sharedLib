def call() {
    pipeline {
        agent any
        environment {
            AWS_CRED = 'cloud_user'
            AWS_REGION = 'us-east-1'
            s3Bucket = 'filesbucket-anne'
        }
        stages {
             stage('Upload Templates') {                  
                steps { 
                   uploadTemplates_s3(s3Bucket: "${s3Bucket}")
                }
            } 
            /*stage('Create Bucket') {                  
                steps {
                    createBucket()
                }
            }*/
            stage('Upload Files to S3') {                  
                steps {
                    uploadAllFileS3(s3Bucket:"filesbucket-anne")
                }
            }
            /*stage('Upload Sample File to S3') {                  
                steps {
                    uploadSampleFileS3(s3Bucket:"filesbucket-anne", sampleFile: "sampleFile.txt")
                }
            }*/
            stage('Delete a file from S3 bucket') {                  
                steps {
                    deleteFileFromS3(s3Bucket: "filesbucket-anne", pathName: "Hey.txt")
                }
            }
            stage('Deploy EC2') {                  
                steps {
                    ec2Deploy(stackName: "EC2Jenkins-Anne", s3Bucket: "filesbucket-anne")
                }
            }
        }
    }
}
