package com.ajay.dual.space.dual.parallelspace

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajay.dual.space.dual.parallelspace.databinding.ActivityRateUsBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RateUs : AppCompatActivity() {

    private lateinit var binding: ActivityRateUsBinding
    private lateinit var loadingDialog: LoadingDialog

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Load the interstitial ad
//        FacebookAdsManager.loadInterstitialAd(
//            this,
//            getString(R.string.facebook_Interstertial)
//        )
//        // Load the interstitial ad
//        AdManager.loadInterstitialAd(this)
        loadingDialog = LoadingDialog(this)
//        bannerAdsAdmob()
        // Initialize ProgressBar
        loadingDialog.show()
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            loadingDialog.dismiss()
//            // Attempt to show Facebook Interstitial Ad
//            if (FacebookAdsManager.isInterstitialAdLoaded()) {
//                FacebookAdsManager.showInterstitialAd(this@RateUs)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                // If Facebook ad is not loaded but AdManager's Interstitial Ad is loaded, show AdManager's Interstitial Ad
//                AdManager.showInterstitialAd(this@RateUs)
//            } else {
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Google Admob ad is closed
//            AdManager.performActionWhenAdClosed(this@RateUs) {
//                // Hide loading indicator after the task is performed
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Facebook ad is closed
//            FacebookAdsManager.performActionWhenAdClosed {
//                // Hide loading indicator after the task is performed
//                loadingDialog.dismiss()
//            }
        }
        setUpButtonClickListener()
    }

    private fun bannerAdsAdmob() {
        FacebookAdsManager.loadBannerAd(
            this,
            getString(R.string.facebook_Banner),
            binding.bannerContainer
        )
        AdManager.loadBannerAd(this, binding.adView)
    }

    private fun setUpButtonClickListener() {
        binding.ratingBtn.setOnClickListener {
            val appPackageName = packageName
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                // If Google Play Store app is not available, open the Play Store website
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
    }
}