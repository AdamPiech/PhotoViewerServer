package storage;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import software.amazon.awssdk.async.AsyncRequestProvider;
import software.amazon.awssdk.async.AsyncResponseHandler;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.utils.FunctionalUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static utils.Util.BUCKET;

/**
 * Created by Adam Piech on 2017-09-04.
 */

public class S3Store {

    private static final String KEY = "";

    public static void putObject(String filePath) {
        DefaultAWSCredentialsProviderChain credentialProviderChain = new DefaultAWSCredentialsProviderChain();
        TransferManager tm = new TransferManager(credentialProviderChain.getCredentials());
        Upload upload = tm.upload(BUCKET, KEY, new File(filePath));
        try {
            upload.waitForCompletion();
        } catch (AmazonClientException amazonClientException) {
            amazonClientException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tm.shutdownNow();
    }

    public static void getObject(String filePath) {
        DefaultAWSCredentialsProviderChain credentialProviderChain = new DefaultAWSCredentialsProviderChain();
        TransferManager tm = new TransferManager(credentialProviderChain.getCredentials());
        Download download = tm.download(BUCKET, KEY, new File(filePath));
        try {
            download.waitForCompletion();
        } catch (AmazonClientException amazonClientException) {
            amazonClientException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tm.shutdownNow();
    }

    public static void putObjectAsync(String filePath) {
        S3AsyncClient client = S3AsyncClient.create();
        CompletableFuture<PutObjectResponse> future = client.putObject(
                PutObjectRequest.builder()
                        .bucket(BUCKET)
                        .key(KEY)
                        .build(),
                AsyncRequestProvider.fromFile(Paths.get(filePath))
        );
        future.whenComplete((resp, err) -> {
            try {
                if (resp == null) {
                    err.printStackTrace();
                }
            } finally {
                FunctionalUtils.invokeSafely(client::close);
            }
        });
    }

    public static void getObjectAsync(String filePath) {
        S3AsyncClient client = S3AsyncClient.create();
        final CompletableFuture<Void> future = client.getObject(
                GetObjectRequest.builder()
                        .bucket(BUCKET)
                        .key(KEY)
                        .build(),
                AsyncResponseHandler.toFile(Paths.get(filePath)));
        future.whenComplete((resp, err) -> {
            try {
                if (resp == null) {
                    err.printStackTrace();
                }
            } finally {
                FunctionalUtils.invokeSafely(client::close);
            }
        });
    }

    public static void putObjects() {
    }

    public static void getObjects() {
    }

    public static List<String> listObjects() {
        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
        List<String> objectKeys = new ArrayList<>();
        try {
            ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(BUCKET).withMaxKeys(2);
            ListObjectsV2Result result;
            do {
                result = s3client.listObjectsV2(req);
                for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                    objectKeys.add(objectSummary.getKey());
                }
                req.setContinuationToken(result.getNextContinuationToken());
            } while(result.isTruncated() == true );
            s3client.shutdown();
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
        return objectKeys;
    }

    public static void deleteObject(String key) {
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.deleteObject(BUCKET, key);
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        }
        s3.shutdown();
    }

    public static void deleteObjects(String... keys) {
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.deleteObjects(new DeleteObjectsRequest(BUCKET).withKeys(keys));
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        }
        s3.shutdown();
    }

}