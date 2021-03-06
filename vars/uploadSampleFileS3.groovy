def call(Map stageParams = [:]) {
    withAWS(region: "${AWS_REGION}", credentials:"${AWS_CRED}") {       
        s3Upload( 
            bucket: stageParams.s3Bucket,
            file: "${stageParams.sampleFile}"                       
        )
    }
}
//awsRegion: "us-east-1", s3Bucket: "filesbucket-anne", sampleFile: "sampleFile.txt"
