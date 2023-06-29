package com.demo.structure.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityAdmobBinding;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.regex.Matcher;

public class AdmobActivity extends AppCompatActivity {


    ActivityAdmobBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admob);
        MobileAds.initialize(this);
        bannerAdinit();
        intestraladinit();
        nativeAdinit();//working
    }

    private void nativeAdinit() {
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd NativeAd) {
                        Log.e("TAG", "onAdFailedToLoad: ");
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        Log.e("TAG", "onAdFailedToLoad: ");
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        adLoader.loadAds(new AdRequest.Builder().build(), 3);
    }

    private InterstitialAd mInterstitialAd;

    private void intestraladinit() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // super.onAdFailedToLoad(loadAdError);
                mInterstitialAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // super.onAdLoaded(interstitialAd);
                mInterstitialAd = interstitialAd;
                loadIntestrealAd(mInterstitialAd);
            }
        });

    }

    private void loadIntestrealAd(InterstitialAd mInterstitialAd) {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                // super.onAdClicked();
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                //super.onAdDismissedFullScreenContent();
                // mInterstitialAd = null;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                //super.onAdFailedToShowFullScreenContent(adError);
                // mInterstitialAd = null;
            }

            @Override
            public void onAdImpression() {
                // super.onAdImpression();
            }

            @Override
            public void onAdShowedFullScreenContent() {
                //super.onAdShowedFullScreenContent();
            }
        });

        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    private void bannerAdinit() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.e("TAG", "onInitializationComplete: " + initializationStatus.getAdapterStatusMap().toString());
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        binding.adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
    }


}