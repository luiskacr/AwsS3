package com.luis.aws_s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

/**
 * This class establishes all the variables necessary to log in with AWS S3
 *
 * @author luis_
 */
public class Credentials {
    /**
     * To use this project, you must have an IAM user with the necessary permissions to execute all the actions
     */

    //Access key ID from the IAM user with access to the bucket
    private String ACCESS_KEY_ID = "";
    //Access Secret Key from the IAM user with access to the bucket
    private String ACCESS_SEC_KEY = "";
    //Bucket  Name from S3
    private String BUCKET_NAME = "";
    //Name of the default folder to use
    private String FOLDER_NAME = "";
    //AWS Region bucket
    private String REGION = "";

    //Default Fields
    private final String SUFFIX = "/";
    private String RUTE_KEY = FOLDER_NAME + SUFFIX;
    
    public Credentials() {
    }
    
    /**
     * 
     * @return Access key for Conection
     */
    public String getACCESS_KEY_ID() {
        return ACCESS_KEY_ID;
    }
    
    /**
     * Set Access key for Conection
     * @param ACCESS_KEY_ID 
     */
    public void setACCESS_KEY_ID(String ACCESS_KEY_ID) {
        this.ACCESS_KEY_ID = ACCESS_KEY_ID;
    }

    public String getACCESS_SEC_KEY() {
        return ACCESS_SEC_KEY;
    }

    public void setACCESS_SEC_KEY(String ACCESS_SEC_KEY) {
        this.ACCESS_SEC_KEY = ACCESS_SEC_KEY;
    }

    public String getBUCKET_NAME() {
        return BUCKET_NAME;
    }

    public void setBUCKET_NAME(String BUCKET_NAME) {
        this.BUCKET_NAME = BUCKET_NAME;
    }

    public String getFOLDER_NAME() {
        return FOLDER_NAME;
    }

    public void setFOLDER_NAME(String FOLDER_NAME) {
        this.FOLDER_NAME = FOLDER_NAME;
    }

    public String getREGION() {
        return REGION;
    }

    public void setREGION(String REGION) {
        this.REGION = REGION;
    }

    public String getRUTE_KEY() {
        return RUTE_KEY;
    }

    public void setRUTE_KEY(String RUTE_KEY) {
        this.RUTE_KEY = RUTE_KEY;
    }

    /**
     * This method makes the connection with AWS S3
     *
     * @return Returns an AmazonS3 connection type object If there is no
     * connection it returns a null object and the error
     */
    public AmazonS3 AWSConnection() {
        AmazonS3 s3Client = null;
        try {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESS_KEY_ID, ACCESS_SEC_KEY);
            s3Client = AmazonS3ClientBuilder.standard().withRegion(REGION).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
            return s3Client;
        } catch (AmazonS3Exception e) {
            System.out.println("Error Conection");
            System.out.println(e);
            return s3Client;
        }
    }
    
}
