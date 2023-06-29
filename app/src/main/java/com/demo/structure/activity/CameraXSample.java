package com.demo.structure.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityCameraXsampleBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class CameraXSample extends AppCompatActivity {


    ActivityCameraXsampleBinding binding;
    ImageCapture imageCapture;
    VideoCapture videoCapture;
    ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera_xsample);

        reqestPermission();
        initViews();
    }

    BehaviorSubject<String> emailSubject = BehaviorSubject.create();

    private void initViews() {
        binding.imageCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        binding.videoCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureVideo();
            }
        });
        service = Executors.newSingleThreadExecutor();
    }


    private void captureVideo() {

    }

    private void takePhoto() {
        imageCapture = new ImageCapture.Builder().build();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);


        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, formattedDate);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image");
        }
        ImageCapture.OutputFileOptions options =new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues).build();
        imageCapture.takePicture(options, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Toast.makeText(getApplicationContext(),"Image Saved Succesfully",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {


            }
        });
    }

    private void reqestPermission() {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        String[] permissions2 = new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (!checkPermissionGranted(permissions)) {
                ActivityCompat.requestPermissions(
                        this, permissions, 2000);
            }else{
                startCamera();
            }
        } else {
            if (!checkPermissionGranted(permissions2)) {
                ActivityCompat.requestPermissions(
                        this, permissions2, 2000);
            }else{
                startCamera();
            }
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> future  =ProcessCameraProvider.getInstance(this);
        future.addListener((Runnable) () -> {
            try {
                ProcessCameraProvider cameraProvider = null;
                cameraProvider = future.get();
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(binding.viewFinder.getSurfaceProvider());
                CameraSelector selector = CameraSelector.DEFAULT_BACK_CAMERA;
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this,selector,preview);
            } catch (Exception e){
                Log.e("TAG", "Use case binding failed", e);
            }
        },ContextCompat.getMainExecutor(this));
    }


    private Boolean checkPermissionGranted(String[] permissions) {
        Boolean isGranted = true;
        for (String one : permissions) {
            if (ContextCompat.checkSelfPermission(this, one) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
        }
        return isGranted;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        service.shutdown();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}