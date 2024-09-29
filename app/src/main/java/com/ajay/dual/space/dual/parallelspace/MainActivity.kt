package com.ajay.dual.space.dual.parallelspace

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajay.dual.space.dual.parallelspace.databinding.ActivityMainBinding
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var nativeAdView: NativeAdView

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        setContentView(binding.root)
        // Load the interstitial ad
//        FacebookAdsManager.loadInterstitialAd(
//            this,
//            getString(R.string.facebook_Interstertial)
//        )
//        // Load the interstitial ad
//        AdManager.loadInterstitialAd(this)
//        nativeAdView = findViewById(R.id.native_ad_view)
//        AdManager.loadNativeAd(this, getString(R.string.admob_Native)) { nativeAd ->
//            if (nativeAd != null) {
//                populateNativeAdView(nativeAd, nativeAdView)
//            }
//        }
        loadingDialog = LoadingDialog(this)
//        bannerAdsAdmob()
        // Initialize ProgressBar
        loadingDialog.show()

        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            loadingDialog.dismiss()
//            if (FacebookAdsManager.isInterstitialAdLoaded()) {
//                FacebookAdsManager.showInterstitialAd(this@MainActivity)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                AdManager.showInterstitialAd(this@MainActivity)
//            } else {
//                loadingDialog.dismiss()
//            }
//
//            AdManager.performActionWhenAdClosed(this@MainActivity) {
//                loadingDialog.dismiss()
//            }
//            FacebookAdsManager.performActionWhenAdClosed {
//                loadingDialog.dismiss()
//            }
        }
        setUpButtonClickListener()
    }

//    private fun uploadImageUrl(image: String) {
//        val imageMap = hashMapOf("imageUrl" to image)
//        firestore.collection("images")
//            .add(imageMap)
//            .addOnSuccessListener {
//                Toast.makeText(this, "Image URL uploaded successfully", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener { exception ->
//                Toast.makeText(
//                    this,
//                    "Error uploading image URL: ${exception.message}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//    }

    private fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        adView.mediaView = adView.findViewById(R.id.ad_media)
        // Assign the native ad to the NativeAdView.
        adView.setNativeAd(nativeAd)
    }

    private fun setUpButtonClickListener() {
        binding.clonebtn.setOnClickListener {
            handleButtonClick {
                val intent = Intent(this, AppListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.morebtn.setOnClickListener {
            handleButtonClick {
                val intent = Intent(this, MoreActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.morebtn.setOnClickListener {
            handleButtonClick {
                TelegramUtils.openTelegramToJoinGroup(this)
            }
        }
    }

    private fun bannerAdsAdmob() {
        FacebookAdsManager.loadBannerAd(
            this,
            getString(R.string.facebook_Banner),
            binding.bannerContainer
        )
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun handleButtonClick(actionAfterAd: () -> Unit) {
        // Initialize ProgressBar
        loadingDialog.show()

        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            loadingDialog.dismiss()
            actionAfterAd.invoke()
            //            // Attempt to show Interstitial Ad
//            if (FacebookAdsManager.isInterstitialAdLoaded()) {
//                FacebookAdsManager.showInterstitialAd(this@MainActivity)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                AdManager.showInterstitialAd(this@MainActivity)
//            } else {
//                loadingDialog.dismiss()
//                actionAfterAd.invoke()
//            }
//
//            AdManager.performActionWhenAdClosed(this@MainActivity) {
//                loadingDialog.dismiss()
//                actionAfterAd.invoke()
//            }
//            FacebookAdsManager.performActionWhenAdClosed {
//                loadingDialog.dismiss()
//                actionAfterAd.invoke()
//            }
        }
    }
}