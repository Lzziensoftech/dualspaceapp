package com.ajay.dual.space.dual.parallelspace

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.shashank.sony.fancytoastlib.FancyToast

object AdManager {

    private var mInterstitialAd: InterstitialAd? = null
    private var mNativeAd: NativeAd? = null

    fun loadBannerAd(context: Context, adView: AdView) {
        try {
            MobileAds.initialize(context) {}
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        } catch (e: Exception) {
            (context as? Activity)?.runOnUiThread {
                FancyToast.makeText(
                    context,
                    "Error loading banner ad: ${e.message}",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    false
                ).show()
            }
        }
    }

    fun loadInterstitialAd(context: Context) {
        try {
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(
                context,
                context.getString(R.string.admob_Interstertial),
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                    }
                })
        } catch (e: Exception) {
            FancyToast.makeText(
                context,
                "Error loading interstitial ad: ${e.message}",
                FancyToast.LENGTH_SHORT,
                FancyToast.ERROR,
                false
            ).show()
        }
    }

    fun showInterstitialAd(context: Context) {
        loadInterstitialAd(context)
        try {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(context as Activity)
            } else {
                FancyToast.makeText(
                    context,
                    "Not Loaded",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    false
                ).show()
            }
        } catch (e: Exception) {
            FancyToast.makeText(
                context,
                "Error showing interstitial ad: ${e.message}",
                FancyToast.LENGTH_SHORT,
                FancyToast.ERROR,
                false
            ).show()
        }
    }

    fun performActionWhenAdClosed(context: Context, action: () -> Unit) {
        try {
            val adListener = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    action()
                }
            }
            mInterstitialAd?.fullScreenContentCallback = adListener
        } catch (e: Exception) {
            FancyToast.makeText(
                context,
                "Error performing action when ad is closed: ${e.message}",
                FancyToast.LENGTH_SHORT,
                FancyToast.ERROR,
                false
            ).show()
        }
    }

    fun isInterstitialAdLoaded(): Boolean {
        return mInterstitialAd != null
    }

    // Initialize Google Mobile Ads SDK
    fun initialize(context: Context) {
        MobileAds.initialize(context)
    }

    // Function to load a native ad
    fun loadNativeAd(context: Context, adUnitId: String, onAdLoaded: (NativeAd?) -> Unit) {
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad: NativeAd ->
                mNativeAd = ad
                onAdLoaded(mNativeAd)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//                    FancyToast.makeText(
//                        context,
//                        "Failed to load native ad: ${loadAdError.message}",
//                        FancyToast.LENGTH_SHORT,
//                        FancyToast.ERROR,
//                        false
//                    ).show()
                    onAdLoaded(null)
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }

    // Function to get the loaded native ad
    fun getNativeAd(): NativeAd? {
        return mNativeAd
    }
}