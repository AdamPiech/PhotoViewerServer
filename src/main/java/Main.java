
import image.ImageProcessing;
import image.ImageSimpleOperations;
import software.amazon.awssdk.async.AsyncRequestProvider;
import software.amazon.awssdk.async.AsyncResponseHandler;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.utils.FunctionalUtils;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Adam Piech on 2017-09-01.
 */
public class Main {

    private static final String BUCKET = "photoviewerstore";
    private static final String KEY = "image.png";

    private static final String LINUX_PATH = "~/file.png";

    public static void main(String[] args) {
        getObject(LINUX_PATH);
        putObject(LINUX_PATH);
    }

    private static void putObject(String filePath) {
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
                if (resp != null) {
                    System.out.println(resp);
                } else {
                    err.printStackTrace();
                }
            } finally {
                FunctionalUtils.invokeSafely(client::close);
            }
        });
    }

    private static void getObject(String filePath) {
        S3AsyncClient client = S3AsyncClient.create();
        final CompletableFuture<Void> future = client.getObject(
                GetObjectRequest.builder()
                        .bucket(BUCKET)
                        .key(KEY)
                        .build(),
                AsyncResponseHandler.toFile(Paths.get(filePath)));
        future.whenComplete((resp, err) -> {
            try {

                System.out.println(" ===================== PROCESSING ===================== ");
                BufferedImage image = ImageSimpleOperations.openImage(LINUX_PATH);
                image = ImageProcessing.getImageInSepia(image);
                ImageSimpleOperations.saveImage(image, LINUX_PATH);
                
                if (resp != null) {
                    System.out.println(resp);
                } else {
                    err.printStackTrace();
                }
            } finally {
                FunctionalUtils.invokeSafely(client::close);
            }
        });
    }



}
