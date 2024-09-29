package com.ajay.dual.space.dual.parallelspace

import android.content.Context
import android.widget.LinearLayout
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.AdListener
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener

object FacebookAdsManager {

    private var mInterstitialAd: InterstitialAd? = null
    private var actionWhenAdClosed: (() -> Unit)? = null
    private var mBannerAd: AdView? = null

    fun loadInterstitialAd(context: Context, placementId: String) {
        mInterstitialAd = InterstitialAd(context, placementId)
        val interstitialAdListener = object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad?) {
                // Interstitial ad displayed callback
            }

            override fun onInterstitialDismissed(ad: Ad?) {
                // Interstitial dismissed callback
                actionWhenAdClosed?.invoke()
            }

            override fun onError(ad: Ad?, adError: AdError?) {
                // Interstitial ad error callback
            }

            override fun onAdLoaded(ad: Ad?) {
                // Interstitial ad onAdLoaded callback
            }

            override fun onAdClicked(ad: Ad?) {
                // Interstitial ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad?) {
                // Interstitial ad impression logged callback
            }
        }

        // Set the listener to the interstitial ad
        mInterstitialAd?.loadAd(
            mInterstitialAd?.buildLoadAdConfig()
                ?.withAdListener(interstitialAdListener)
                ?.build()
        )
    }

    fun showInterstitialAd(activity: Context) {
        mInterstitialAd?.show()
        loadInterstitialAd(activity, activity.getString(R.string.facebook_Interstertial))
    }

    fun isInterstitialAdLoaded(): Boolean {
        return mInterstitialAd?.isAdLoaded ?: false
    }

    fun performActionWhenAdClosed(action: () -> Unit) {
        actionWhenAdClosed = action
    }

    fun loadBannerAd(context: Context, placementId: String, adContainer: LinearLayout) {
        mBannerAd = AdView(context, placementId, AdSize.BANNER_HEIGHT_50)

        val adListener = object : AdListener {
            override fun onError(ad: Ad?, adError: AdError?) {
//                val errorMessage = "${adError?.errorMessage}"
//                FancyToast.makeText(
//                    context,
//                    errorMessage,
//                    FancyToast.LENGTH_SHORT,
//                    FancyToast.ERROR,
//                    false
//                ).show()
            }

            override fun onAdLoaded(ad: Ad?) {
                // Check if the banner ad view has a parent before adding it
                if (mBannerAd?.parent == null) {
                    // Banner ad is loaded and ready to be displayed
                    adContainer.removeAllViews()
                    adContainer.addView(mBannerAd)
                }
            }

            override fun onAdClicked(ad: Ad?) {
                // Banner ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad?) {
                // Banner ad impression logged callback
            }
        }

        // Request an ad
        mBannerAd?.loadAd(
            mBannerAd?.buildLoadAdConfig()
                ?.withAdListener(adListener)
                ?.build()
        )
    }
}