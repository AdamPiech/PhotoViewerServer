package storage;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
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
import java.util.concurrent.CompletableFuture;

import static utils.Util.BUCKET;

/**
 * Created by Adam Piech on 2017-09-04.
 */

public class S3Store {

    private static final String KEY = "";

    private static void putObject(String filePath) {

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

    private static void putObjectAsync(String filePath) {

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

    private static void getObject(String filePath) {

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

    private static void getObjectAsync(String filePath) {

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

}
