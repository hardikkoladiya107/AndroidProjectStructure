package com.demo.structure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;
import android.widget.Toast;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityExperimentalBinding;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExperimentalActivity extends AppCompatActivity {


    ActivityExperimentalBinding binding;

    Bitmap bmp, scaledbmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_experimental);
        initViews();


    }

    private void initViews() {
        requestPermission();
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        binding.pdfissuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateAndShare();
            }
        });

    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }
        } else {

        }
    }

    int pageHeight = 1120;
    int pagewidth = 792;

    private void generateAndShare() {
        if(isavailable()){
            return;
        }
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint title = new Paint();
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(scaledbmp, 56, 40, paint);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        title.setTextSize(15);
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));
        canvas.drawText("A portal for IT professionals.", 209, 100, title);
        canvas.drawText("Geeks for Geeks", 209, 80, title);
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));
        title.setTextSize(15);
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("This is sample document which we have created.", 396, 560, title);
        pdfDocument.finishPage(myPage);
        File file = new File(Environment.getExternalStorageDirectory(), "GFG.pdf");
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
    }

    private boolean isavailable() {
        File file = new File(Environment.getExternalStorageDirectory(), "GFG.pdf");
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);

        if(file.exists()){
            intentShareFile.setType("application/pdf");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+file.getAbsolutePath()));
            startActivity(Intent.createChooser(intentShareFile, "Share File"));
            return true;
        }
        return false;
    }


}