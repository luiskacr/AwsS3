/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luis.aws_s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * This class contains all the necessary methods to control the operations of
 * the software that interact with AWS S3
 *
 * @author luis_
 */
public class Operations {

    Credentials c = new Credentials();

    /**
     * Method to create a bucket in S3
     *
     * @param buckettName String with the name of the bucket
     * @return Return Returns a boolean response about the creation of the
     * bucket
     */
    public boolean createBucket(String buckettName) {
        try {
            final AmazonS3 s3 = c.AWSConnection();
            if (s3.doesBucketExistV2(buckettName)) {
                System.err.println("Bucket " + buckettName + " already exists!!");
                return false;
            } else {
                try {
                    s3.createBucket(buckettName);
                    return true;
                } catch (AmazonS3Exception e) {
                    System.err.println("Error creating Buckets!!");
                    System.err.println(e);
                    return false;
                }
            }
        } catch (AmazonS3Exception e) {
            return false;
        }

    }

    public boolean deleatBucket(String buckettName) {

    }

    /**
     * Method to list all available buckets
     *
     * @return A List of Buckets
     */
    public List<Bucket> listBuckets() {
        List<Bucket> buckets = null;
        try {
            final AmazonS3 s3 = c.AWSConnection();
            buckets = s3.listBuckets();
            return buckets;
        } catch (AmazonS3Exception e) {
            System.err.println("Error listing Objects!!");
            System.err.println(e);
            return buckets;
        }
    }

    /**
     * Method to list all files within the bucket
     *
     * @return A object 3ObjectSummary whith
     */
    public List<S3ObjectSummary> listObject() {
        List<S3ObjectSummary> objects = null;
        try {
            final AmazonS3 s3 = c.AWSConnection();
            ListObjectsV2Result result = s3.listObjectsV2(c.getBUCKET_NAME());
            objects = result.getObjectSummaries();
            return objects;
        } catch (Exception e) {
            System.err.println("Error listing Objects!!");
            System.err.println(e);
            return objects;
        }

    }

    /**
     * Method for uploading a Private files to the bucket
     *
     * @param file File to upload
     * @return A Boolean answer if with the answer if the file was uploaded to
     * the Bucket
     */
    public boolean uploadFile(File file) {
        String fileName = file.getName();
        try {
            final AmazonS3 s3 = c.AWSConnection();
            s3.putObject(c.getBUCKET_NAME(), (c.getRUTE_KEY() + fileName), file);
            return true;
        } catch (AmazonS3Exception e) {
            System.err.println("Error Upload File!!");
            System.err.println(e);
            return false;
        }
    }

    /**
     * Method for uploading a Private files to the bucket
     *
     * @param file File to upload
     * @param rute Bucket path in S3 where to put the file
     * @return A Boolean answer if with the answer if the file was uploaded to
     * the Bucket
     */
    public boolean uploadFile(File file, String rute) {
        String fileName = file.getName();
        try {
            final AmazonS3 s3 = c.AWSConnection();
            s3.putObject(c.getBUCKET_NAME(), (rute + fileName), file);
            return true;
        } catch (AmazonS3Exception e) {
            System.err.println("Error Upload File!!");
            System.err.println(e);
            return false;
        }
    }

    /**
     * Method to upload the File to the bucket in S3 in a public way and obtain
     * a URL of where the file is
     *
     * @param file File to upload
     * @return Returns the public URL where the file was stored in the bucket
     */
    public URL uploadPublicFileUrl(File file) {
        String fileName = file.getName();
        URL urlFile = null;
        try {
            final AmazonS3 s3 = c.AWSConnection();
            s3.putObject(new PutObjectRequest(c.getBUCKET_NAME(), (c.getRUTE_KEY() + fileName), file).withCannedAcl(CannedAccessControlList.PublicRead));
            urlFile = s3.getUrl(c.getBUCKET_NAME(), (c.getRUTE_KEY() + fileName));
            return urlFile;
        } catch (AmazonS3Exception e) {
            System.err.println("Error Upload File!!");
            System.err.println(e);
            return urlFile;
        }
    }

    /**
     * Method to upload the File to the bucket in S3 in a public way and obtain
     * a URL of where the file is
     *
     * @param file File to upload
     * @param rute Bucket path in S3 where to put the file
     * @return Returns the public URL where the file was stored in the bucket
     */
    public URL uploadPublicFileUrl(File file, String rute) {
        String fileName = file.getName();
        URL urlFile = null;
        try {
            final AmazonS3 s3 = c.AWSConnection();
            s3.putObject(new PutObjectRequest(c.getBUCKET_NAME(), (rute + fileName), file).withCannedAcl(CannedAccessControlList.PublicRead));
            urlFile = s3.getUrl(c.getBUCKET_NAME(), (c.getRUTE_KEY() + fileName));
            return urlFile;
        } catch (AmazonS3Exception e) {
            System.err.println("Error Upload File!!");
            System.err.println(e);
            return urlFile;
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    public boolean deletfileName(String fileName) {
        try {
            final AmazonS3 s3 = c.AWSConnection();
            s3.deleteObject(c.getBUCKET_NAME(), (c.getRUTE_KEY() + fileName));
            return true;
        } catch (AmazonS3Exception e) {
            System.err.println("Error deleting File!!");
            System.err.println(e);
            return false;
        }
    }

    public boolean deletfileRute(String rute) {
        try {
            final AmazonS3 s3 = c.AWSConnection();
            s3.deleteObject(c.getBUCKET_NAME(), rute);
            return true;
        } catch (AmazonS3Exception e) {
            System.err.println("Error deleting File!!");
            System.err.println(e);
            return false;
        }
    }
}
