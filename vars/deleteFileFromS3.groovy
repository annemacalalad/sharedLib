def call(Map stageParams = [:]) {
    withAWS(region: "${AWS_REGION}", credentials:"${AWS_CRED}") {       
        s3Delete( 
            bucket: stageParams.s3Bucket,
            path: stageParams.pathName                       
        )
    }
}
