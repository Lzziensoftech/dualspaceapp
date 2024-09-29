package com.ajay.dual.space.dual.parallelspace

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajay.dual.space.dual.parallelspace.databinding.ActivityContactUsBinding
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactUs : AppCompatActivity() {

    private lateinit var binding: ActivityContactUsBinding
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var nativeAdView: NativeAdView

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Load the interstitial ad
//        FacebookAdsManager.loadInterstitialAd(this, getString(R.string.facebook_Interstertial))
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
//                FacebookAdsManager.showInterstitialAd(this@ContactUs)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                AdManager.showInterstitialAd(this@ContactUs)
//            } else {
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Google Admob ad is closed
//            AdManager.performActionWhenAdClosed(this@ContactUs) {
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Facebook ad is closed
//            FacebookAdsManager.performActionWhenAdClosed {
//                loadingDialog.dismiss()
//            }
        }
        setUpButtonListner()
    }

    private fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        adView.mediaView = adView.findViewById(R.id.ad_media)
        // Assign the native ad to the NativeAdView.
        adView.setNativeAd(nativeAd)
    }

    private fun bannerAdsAdmob() {
        FacebookAdsManager.loadBannerAd(
            this,
            getString(R.string.facebook_Banner),
            binding.bannerContainer
        )
    }

    private fun setUpButtonListner() {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        binding.copyNumberBtn.setOnClickListener {
            val mobileNumber = binding.mobileNumber.text.toString()
            val clipData = ClipData.newPlainText("MobileNumber", mobileNumber)
            clipboardManager.setPrimaryClip(clipData)
            FancyToast.makeText(
                this,
                "Mobile number copied to clipboard",
                FancyToast.LENGTH_SHORT,
                FancyToast.SUCCESS,
                false
            ).show()
        }
        binding.copyEmailBtn.setOnClickListener {
            val mobileNumber = binding.emailId.text.toString()
            val clipData = ClipData.newPlainText("MobileNumber", mobileNumber)
            clipboardManager.setPrimaryClip(clipData)
            FancyToast.makeText(
                this,
                "Email ID copied to clipboard",
                FancyToast.LENGTH_SHORT,
                FancyToast.SUCCESS,
                false
            ).show()
        }

        binding.messageWtpBtn.setOnClickListener {
            val indianMobileNumber =
                "+91998081XXX" // Replace with the desired Indian mobile number
            val message = "Hi Sir I Want Help / Want a Developer"

            val uri =
                Uri.parse("https://api.whatsapp.com/send?phone=$indianMobileNumber&text=$message")
            val whatsappIntent = Intent(Intent.ACTION_VIEW, uri)

            if (whatsappIntent.resolveActivity(packageManager) != null) {
                startActivity(whatsappIntent)
            } else {
                val businessUri =
                    Uri.parse("https://api.whatsapp.com/send?phone=$indianMobileNumber&text=$message")
                val whatsappBusinessIntent = Intent(Intent.ACTION_VIEW, businessUri)

                if (whatsappBusinessIntent.resolveActivity(packageManager) != null) {
                    startActivity(whatsappBusinessIntent)
                } else {
                    FancyToast.makeText(
                        this,
                        "WhatsApp or WhatsApp Business App Not Found",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.WARNING,
                        false
                    ).show()
                }
            }
        }
    }
}