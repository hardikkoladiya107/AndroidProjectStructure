package com.demo.structure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityHtmlParseBinding;

import java.util.regex.Pattern;

public class HtmlParseActivity extends AppCompatActivity {

    ActivityHtmlParseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_html_parse);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseHtml();
            }
        });
    }

    private void parseHtml() {
        String text = "<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=windows-1252\"><title>Logon failed</title><style>body { background: #FFFFFF; text-align: center; width:100%; height:100%; overflow:hidden; }.content { display: table; position:absolute; width:100%; height:80%; }.valigned { display: table-cell; vertical-align: middle; }.lowerCenter { display: table-cell; vertical-align: bottom; }.footer { position: absolute; bottom: 0; left: 0; width: 100%; z-index: -1; }.footerLeft { float: left; margin-left: 20px; }.footerRight { float: right; margin-right: 20px; position: absolute; bottom: 0px; right: 0px; }.centerText { font-style: normal; font-family: Arial; font-size: 26px; color: #444444; z-index: 1; }.errorTextHeader { font-style: normal; font-family: Arial; font-size: 40px; color: #444444; }.bottomText { align: center; font-style: normal; font-family: Arial; font-size: 14px; color: #444444; }.biggerBottomText { align: center; font-style: normal; font-family: Arial; font-size: 16px; color: #444444; }.detailTable { align: bottom; vertical-align: middle; margin-left:auto; margin-right:auto; font-style: normal; font-family: Arial; font-size: 16px; color: #444444; }</style></head><body><div class=\"content\"><div class=\"valigned\"><p class=\"centerText\"><span class=\"errorTextHeader\"> 401 Not authorized </span></p></div></div><div class=\"footer\"><div class=\"footerLeft\"><img width='150' height='80' title='' alt='SAP logo'src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJYAAABQCAYAAAGMt7zdAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAFhpJREFUeNpiZEAAJiBmZCAd/IdhmGYWhu0ffzNQAjz5mVigTGa291/JNudXlBQHA5K32P////8DWQHv9LsMnzOVGWTm3mf4+OsfmI0sBwNfslQ4gdRPkDeZkA24/OoHg1DHFTCGaX6SrMjA+vE7mA0SBwEQH4SRDUI2h120+sx/GDh67xOYhokhy8H4QD0cuCKMfcXJl/8lCo7+xwYevf0BpkHyIIzPIFiYYQDp9AP/Vx17jsLHZxBMkE02cc9PQrH2eL4L1jBCN4wZaiMzDlv/gVIAFP/HZRlAADEi0YzUyAGMYFdt//iNgtTPCiT/sMAMIzcHAFM/PzQY4BlcCDkWeabdwcqG8WEYqE8AGs5wg+AAlvphKR6W2mFyM868BecMaOoXBAp/BuK/DGhFD4rLPnz/8x8fQHcRA7zogQLJwmNg+nm/FQM/B4Y6uPyLCdZYXYTTZbDUDsJoqR+rixjQ0hc/MAe8J5D68boI2TA2UP7EkWj/Qw34gc8gEAAIIEY07zJBvcHEQF/wD4r/wnITC5IDQWwO5vWvPzIMAPgbKMqNFKIMLGjRAKyjILn9W6IcXJPFxhcMl979gvN3eorD2e7bX2IVP/TiB0PrecJ+/J4kLwiN2t/QUPuPHJWgqAOVIdzAxPkWpsl79SOGLEMhBm8VHgb+/ptgsUwjQYYOezEUw2FyHwvVwfSRJ98Y5PhYwRhdDQx8KtLA6iCMfIkNbA2FhJpI00UGmBUwR4HE3tTpg9k5WjwMM0++gesLmHcbTMPkwSXPZ0j9/rbeAK+DsOVzUE4SEi898R+G8QGQfPeuJyh8bODjtz9gOWj5gK++wporsUYluUA65zCc/WyqHUkhhCsq/8um7qc4h4F8+WSOE1kOQnfYf6jm30y//1LkqEcLXQWg7RCyHIStgIUVGeQ2tpE9+J9cB8EAQAAx4mgHMVDgOFI9gt6GQnEAclXESMcC/z9SVfQPvSpiBjbufjIMFPDkZ8daPzL/+DUg7vm/ZbbsP7RcCG9LM3//TXcH/V3dpfRv/YR3yDkWucxiZP4BcdQsW2GGGBVuMHvzo+8M4XtfoxgEq5g3P/zGMOXaZ4zKGgQW3/7CsOQO/ub579VtKn+3z3yNrbJmhHVjgM3sz8idT/ROKrhfmaTIwM/OhCGOSx96JxYGfq7vUvm9e9ZLaNfoD3ofAQ6QDc7c+RyM4Qp//AFjZAchiyMDUAsCGSiwMcLVgfCvNe04HYS39bDy/FsUGlfr+1GWCoNi/3UUMb8l98D0uwodMD3dVZzBb9l9SJTdO+34a99cnA5iwNc8BhkIwlM9pRiYfv0FY5gl4Ab+B0hO5WdnBsuhGApVD1f7/ieY//fmCccvs5PO4HMQA1pzmQd92AB9CAEGZh59gcLHpw8mLxAzwwFkB7QTwkSomoEndGBb6DO8LNMWZJgfrwZX+PH7XwZ+TkgkSpSdhHTQuswJZnmQ2l+Pzjm+X5lFVAhhOEqi4OhnWSF2hsfvEAU7qOdIKoD1KsG97yfnHN+tySXaQRgJfWKEMkOYuTjlbStoM+jn0/OO7zbkk+QgDEcx/sPe4vj0/Q8DHydEqUzGQRS5JzPsMYP/zz+Gv19e5b4lw0EY0SeTsu8zVaoOoIOer4hYQI6D0Fug/xn//qPcQV+BDloVRbaDYI6CxdlfXNFHtIO+vcl5thruoL/kOAi9kccM7eGwkDgC9x9p3OAvNHTIdhC2gRBqDAmS3WmAAYAAYsST+LGxhzNA7vCgszGKKka0mGRGGt5iwtH5GcoBgzEYjTSk9g8tJf7H1qpCHvtjYdr87jPDCAP/962Q+t+f9RaaILAOYKGnGnBgDUQ3Z6DA33X9Cv9WdLxFGlX+j6/FwIiOB6qjSk/wewWwy7dt+hukWukvUjbEWWZhFPSwntS1CBkGeV4Woix/+PkPg9W6ZwwffiFqQj1hNobjQVJY1XPPfoAh9jVVgWjPHn7+g8FjywvSA2ktsNe3c9ZbaCD9YUAb/seWshixDKPB2g9sDz/+eos8SE0MaD/5jqH9FOrA6qdcVZzqL7/5yWC9/BHR6vEBvsm3Car5ualL5deeOSQFEjEpiwFbQAn0XMeqFtTHB6m//PonSk/vQ4kmXsfrirAz2ImxMRx5jH8yFN1ebOZ2WgkzVO5/iVX/j609Kj8PzCMrkAh253EBkCNBeJmfNIMg4z8G5p9/wPjLp18M1558hfNBGKQGHVTtfoYhtiVcHkUfNoAsD8IzTr3BUKMHDHh0df9uHHf8WKolCAwoUCj+gDbhfyMFGNGNVbzZUKTu3NvXjYZEB2TX/ucM3Qcg5YeOBCfD/kwNDDWi9ecZPDX4GRZFKmGVAwFsdh598AVuLrZpYBAw6r8KH5P59eCM46dFaRcoSUkkBZZYBaLwkRVgZ9ibr43TochArfEcw616Iwxx8crTcPbLdlPMEbOzbxjy1tzHKkeMnR9//GX49eis44dlmVQNJKICS6LoON";
        final Pattern ptrn = Pattern.compile("<font>(.+?)</font>");
        String plainTextFromHTML = Html.fromHtml(text).toString().replaceAll("\n", "").trim();
    }
}