package com.ajay.dual.space.dual.parallelspace

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajay.dual.space.dual.parallelspace.databinding.ActivityShareUsBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ShareUs : AppCompatActivity() {

    private lateinit var binding: ActivityShareUsBinding
    var applink: String = "For the Unlimited Group Join Download the app \n " +
            "https://play.google.com/store/apps/details?id=com.ajay.dual.space.dual.parallelspace"
    private lateinit var loadingDialog: LoadingDialog

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        // Load the interstitial ad
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
//                FacebookAdsManager.showInterstitialAd(this@ShareUs)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                // If Facebook ad is not loaded but AdManager's Interstitial Ad is loaded, show AdManager's Interstitial Ad
//                AdManager.showInterstitialAd(this@ShareUs)
//            } else {
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Google Admob ad is closed
//            AdManager.performActionWhenAdClosed(this@ShareUs) {
//                // Hide loading indicator after the task is performed
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Facebook ad is closed
//            FacebookAdsManager.performActionWhenAdClosed {
//                // Hide loading indicator after the task is performed
//                loadingDialog.dismiss()
//            }
        }
        setUpButtonListner()
    }

    private fun bannerAdsAdmob() {
        FacebookAdsManager.loadBannerAd(
            this,
            getString(R.string.facebook_Banner),
            binding.bannerContainer
        )
        AdManager.loadBannerAd(this, binding.adView)
    }

    private fun setUpButtonListner() {
        binding.shareBtn.setOnClickListener {
            if (isWhatsAppInstalled()) {
                shareLinkToWhatsApp(applink)
            } else if (isWhatsAppBusinessInstalled()) {
                shareLinkToWhatsAppBusiness(applink)
            } else {
                FancyToast.makeText(
                    this,
                    "WhatsApp is not installed.",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.WARNING,
                    false
                ).show()
            }
        }
        binding.shareOtherBtn.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, applink)
            sendIntent.type = "text/plain"

            // Start the activity to show the sharing menu
            startActivity(Intent.createChooser(sendIntent, "Share link using"))

        }
        binding.copyShareLinkBtn.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Link", applink)
            clipboardManager.setPrimaryClip(clipData)
            FancyToast.makeText(
                this,
                "App Link Copied to clipboard",
                FancyToast.LENGTH_SHORT,
                FancyToast.SUCCESS,
                false
            ).show()
        }
    }

    private fun shareLinkToWhatsApp(link: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, link)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.whatsapp")

        try {
            startActivity(sendIntent)
        } catch (e: Exception) {
            FancyToast.makeText(
                this,
                "WhatsApp is not installed.",
                FancyToast.LENGTH_SHORT,
                FancyToast.WARNING,
                false
            ).show()
        }
    }

    private fun shareLinkToWhatsAppBusiness(link: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, link)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.whatsapp.w4b") // WhatsApp Business package name

        try {
            startActivity(sendIntent)
        } catch (e: Exception) {
            FancyToast.makeText(
                this,
                "WhatsApp Business is not installed.",
                FancyToast.LENGTH_SHORT,
                FancyToast.WARNING,
                false
            ).show()
        }
    }

    private fun isWhatsAppInstalled(): Boolean {
        val packageManager = packageManager
        return try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun isWhatsAppBusinessInstalled(): Boolean {
        val packageManager = packageManager
        return try {
            packageManager.getPackageInfo("com.whatsapp.w4b", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}