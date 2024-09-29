package com.ajay.dual.space.dual.parallelspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.ajay.dual.space.dual.parallelspace.databinding.ActivityMoreBinding
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoreBinding
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var nativeAdView: NativeAdView

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreBinding.inflate(layoutInflater)
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
//            // Attempt to show Facebook Interstitial Ad
//            if (FacebookAdsManager.isInterstitialAdLoaded()) {
//                FacebookAdsManager.showInterstitialAd(this@MoreActivity)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                // If Facebook ad is not loaded but AdManager's Interstitial Ad is loaded, show AdManager's Interstitial Ad
//                AdManager.showInterstitialAd(this@MoreActivity)
//            } else {
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Google Admob ad is closed
//            AdManager.performActionWhenAdClosed(this@MoreActivity) {
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

    private fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        adView.mediaView = adView.findViewById(R.id.ad_media)
        // Assign the native ad to the NativeAdView.
        adView.setNativeAd(nativeAd)
    }

    private fun setUpButtonClickListener() {
        binding.contactUsBtn.setOnClickListener {
            handleButtonClick {
                startActivity(Intent(this, ContactUs::class.java))
            }
        }

        binding.joinTelegram.setOnClickListener {
            handleButtonClick {
                TelegramUtils.openTelegramToJoinGroup(this)
            }
        }

        binding.privacyPolicyBtn.setOnClickListener {
            handleButtonClick {
                showTermsAndConditionsDialog()
            }
        }

        binding.promoteBtn.setOnClickListener {
            handleButtonClick {
                startActivity(Intent(this, ContactUs::class.java))
            }
        }

        binding.rateUsBtn.setOnClickListener {
            handleButtonClick {
                startActivity(Intent(this, RateUs::class.java))
            }
        }

        binding.shareUsBtn.setOnClickListener {
            handleButtonClick {
                startActivity(Intent(this, ShareUs::class.java))
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
        loadingDialog.show()
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)

            loadingDialog.dismiss()
            actionAfterAd.invoke()
//            // Attempt to show Interstitial Ad
//            if (FacebookAdsManager.isInterstitialAdLoaded()) {
//                FacebookAdsManager.showInterstitialAd(this@MoreActivity)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                // If Facebook ad is not loaded but AdManager's Interstitial Ad is loaded, show AdManager's Interstitial Ad
//                AdManager.showInterstitialAd(this@MoreActivity)
//            } else {
//                loadingDialog.dismiss()
//                actionAfterAd.invoke()
//            }

//            // Perform the task after the Google Admob ad is closed
//            AdManager.performActionWhenAdClosed(this@MoreActivity) {
//                loadingDialog.dismiss()
//                actionAfterAd.invoke()
//            }
//
//            // Perform the task after the Facebook ad is closed
//            FacebookAdsManager.performActionWhenAdClosed {
//                loadingDialog.dismiss()
//                actionAfterAd.invoke()
//            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showTermsAndConditionsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_terms, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        //    <li><a href="https://www.adcolony.com/privacy-policy/" target="_blank" rel="noopener noreferrer">AdColony</a></li>
        //    <li><a href="https://developers.ironsrc.com/ironsource-mobile/air/ironsource-mobile-privacy-policy/" target="_blank" rel="noopener noreferrer">ironSource</a></li>
        //    <li><a href="https://yandex.com/legal/mobileads_sdk_agreement/" target="_blank" rel="noopener noreferrer">Yandex Ads</a></li>
        //    <li><a href="https://www.mintegral.com/en/privacy/" target="_blank" rel="noopener noreferrer">Mintegral</a></li>
        //    <li><a href="https://www.pangleglobal.com/privacy/enduser-en" target="_blank" rel="noopener noreferrer">Pangle</a></li>
        //    <li><a href="https://www.tapjoy.com/legal/general/privacy-policy/" target="_blank" rel="noopener noreferrer">Tapjoy</a></li>
        //    <li><a href="https://support.chartboost.com/en/legal/privacy-policy" target="_blank" rel="noopener noreferrer">Chartboost</a></li>
        //    <li><a href="https://developer.digitalturbine.com/hc/en-us/articles/7594524218257-Google-s-Data-Safety-Form" target="_blank" rel="noopener noreferrer">DTExchange</a></li>
        //    <li><a href="https://unity3d.com/legal/privacy-policy" target="_blank" rel="noopener noreferrer">Unity Ads</a></li>
        //    <li><a href="https://cas.ai/privacy-policy" target="_blank" rel="noopener noreferrer">Clever Ads Solutions</a></li>
        //    <li><a href="https://www.applovin.com/privacy/" target="_blank" rel="noopener noreferrer">AppLovin</a></li>
        //    <li><a href="https://vungle.com/privacy/" target="_blank" rel="noopener noreferrer">Vungle</a></li>
        //    <li><a href="https://kidoz.net/privacy-policy/" target="_blank" rel="noopener noreferrer">Kidoz</a></li>
        //    <li><a href="https://www.inmobi.com/privacy-policy/" target="_blank" rel="noopener noreferrer">InMobi Ads</a></li>
        //    <li><a href="https://legal.my.com/us/mytarget/privacy/" target="_blank" rel="noopener noreferrer">My.com</a></li> //    <li><a href="https://cas.ai/privacy-policy-3/" target="_blank" rel="noopener noreferrer">CAS</a></li>

        // Initialize views
        val checkBox1 = dialogView.findViewById<CheckBox>(R.id.checkbox1)
        val checkBox2 = dialogView.findViewById<CheckBox>(R.id.checkbox2)
        val checkBox3 = dialogView.findViewById<CheckBox>(R.id.checkbox3)
        val acceptButton = dialogView.findViewById<Button>(R.id.button_accept)
        val textViewHtml = dialogView.findViewById<TextView>(R.id.textViewHtml)

        // Set HTML content to the TextView
        val htmlContent = """
    <!DOCTYPE html>
    <html>
    <body>
    <strong>Disclaimer and Trademark Notice :</strong> <p>
                Permissions: "Dual Space" have applied much system permissions to ensure that applications cloned in "Dual Space" will run normally and smoothly. But "Dual Space" 
                does not collect your personal information. Resources: "DUAL Space" App does not use any additional device memory, battery, or data to run the app. For Legal Notice Mail Us: yadav09062003@gmail.com
                </p>
    <br>
    <strong>Terms &amp; Conditions</strong> <p>
                  By downloading or using the app, these terms will automatically apply to you – you should make sure therefore that you read them carefully before using the app. You’re not allowed to copy or 
                  modify the app, any part of the app, or our trademarks in any way. You’re not allowed to attempt to extract the source code of the app, and you also shouldn’t try to translate the app into other
                   languages or make derivative versions. The app itself, and all the trademarks, copyright, database rights, and other intellectual property rights related to it, still belong to "ABC Software Technologies".
                </p>
                    <br>
                <p><strong>Use of the App:</strong></p> <p>
               The "DUAL Space" app is intended for Cloning Apps. Users can make copy of android Apps. Users must not engage in any activities that violate the law or infringe upon the rights of others while 
               using the App.
                </p>
                    <br>
                <p><strong>User Conduct:</strong></p> <p>
                Users are solely responsible for the content of their messages sent through the App. Users must not engage in any harassing, abusive, or otherwise objectionable behavior while using the App.
                </p>
                    <br>
                <p><strong>Governing Law:</strong></p> <p>
                These terms and conditions shall be governed by and construed in accordance with the laws of All Country, and any disputes relating to these terms and conditions shall be subject to the exclusive 
                jurisdiction of the courts of All Country.
                </p>
                    <br>
                <p><strong>Privacy Policy:</strong></p> <p>
                 "ABC Software Technologies" built the "DUAL Space" app as a Free app. This SERVICE is provided by "ABC Software Technologies" at no cost and is intended for use as is.This page is used to 
                 inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.If you choose to use my Service, then you agree to the 
                 collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information
                  with anyone except as described in this Privacy Policy. The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which are accessible at "DUAL Space" unless 
                  otherwise defined in this Privacy Policy.
                </p>
                    <br>
                 <p><strong>App Permissions:</strong></p> <p>
                 - ACCESS_NETWORK_STATE :- Allows applications to access information about networks.

                 - INTERNET :-  Allows applications to access information about networks.

                 - INTERNET :- Allows applications to open network sockets.

                 - INSTALLED APPLICATIONS / PACKAGE NAME OF APPS
                </p>
                    <br>
                <p><strong>Log Data:</strong></p> <p>
                 I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third-party products) on your phone called Log Data. This Log Data 
                 may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of 
                 your use of the Service, and other statistics. 
                </p>
                    <br>
                <p><strong>Cookies:</strong></p> <p>
                 Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your 
                 device's internal memory. This Service does not use these “cookies” explicitly. However, the app may use third-party code and libraries that use “cookies” to collect information and improve 
                 their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use
                  some portions of this Service.
                </p>
                <br>
                <p>
                  "ABC Technologies" is committed to ensuring that the app is as useful and efficient as possible. For that reason, we reserve the right to make changes to the app or to charge for
                  its services, at any time and for any reason. We will never charge you for the app or its services without making it very clear to you exactly what you’re paying for.
                </p>
              <br>
                <p>
                  The "DUAL Space" stores and processes personal data that you have provided to us, to provide my Service. It’s your responsibility to keep your phone and access to the app secure. We therefore 
                  recommend that you do not jailbreak or root your phone, which is the process of removing software restrictions and limitations imposed by the official operating system of your device. It could
                   make your phone vulnerable to malware/viruses/malicious programs, compromise your phone’s security features and it could mean that the "Multi Space" won’t work properly or at all.
                </p> 
                <br>
                <div><p>
                    The app does use third-party services that declare their Terms and Conditions.
                  </p> <p>
                    Link to Terms and Conditions of third-party service
                    providers used by the app
                  </p> 
                 <ul>
    <li><a href="https://policies.google.com/terms" target="_blank" rel="opener referrer">Google Play Services</a></li>
    <li><a href="https://www.google.com/analytics/terms/" target="_blank" rel="noopener noreferrer">Google Analytics for Firebase</a></li>
    <li><a href="https://firebase.google.com/terms/crashlytics" target="_blank" rel="noopener noreferrer">Firebase Crashlytics</a></li>
    <li><a href="https://policies.google.com/technologies/ads" target="_blank" rel="noopener noreferrer">Google AdMob</a></li>
    <li><a href="https://developers.facebook.com/docs/audience-network/policy/" target="_blank" rel="noopener noreferrer">Facebook Ads</a></li>
                </ul> 
                 <p>
              
                  You should be aware that there are certain things that "ABC Software Technologies" will not take responsibility. Certain functions of the app will require the app to have an active internet 
                  connection. The connection can be Wi-Fi or provided by your mobile network provider, but "Lzzien Software Technologies" cannot take responsibility for the app not working at full functionality 
                  if you don’t have access to Wi-Fi, and you don’t have any of your data allowance left.
                </p> <p></p> 
                <br>
                <p>
                  If you’re using the app outside of an area with Wi-Fi, you should remember that the terms of the agreement with your mobile network provider will still apply.
                  As a result, you may be charged by your mobile provider for the cost of data for the duration of the connection while accessing the app, or other third-party charges.
                  In using the app, you’re accepting responsibility for any such charges, including roaming data charges if you use the app outside of your home territory (i.e. region or country) without 
                  turning off data roaming. If you are not the bill payer for the device on which you’re using the app, please be aware that we assume that you have received permission from the bill payer 
                  for using the app.
                </p>
                 <br>
                 <p>
                  Along the same lines, "ABC Software Technologies" cannot always take responsibility for the way you use the app i.e. 
                  You need to make sure that your device stays charged – if it runs out of battery and you can’t turn it on to avail the Service, "ABC Software Technologies" cannot accept responsibility.
                </p>
                     <br>
                 <p>
                  With respect to "ABC Software Technologies" responsibility for your use of the app, when you’re using the app, it’s important to bear in mind that although we endeavor to ensure that it is 
                  updated and correct at all times, we do rely on third parties to provide information to us so that we can make it available to you. 
                  "AB Software Technologies" accepts no liability for any loss, direct or indirect, you experience as a result of relying wholly on this functionality of the app.
                </p>
                <br>
                <p>
                  At some point, we may wish to update the app. The app is currently available on Android – the requirements for the system(and for any additional systems we decide to extend the availability of 
                  the app to) may change, and you’ll need to download the updates if you want to keep using the app. "ABC Software Technologies" does not promise that it will always update the app so that it is 
                  relevant to you and/or works with the Android version that you have installed on your device. However, you promise to always accept updates to the application when offered to you, We may
                  also wish to stop providing the app, and may terminate use of it at any time without giving notice of termination to you. Unless we tell you otherwise, upon any termination, (a) the
                  rights and licenses granted to you in these terms will end; (b) you must stop using the app, and (if needed) delete it from your device.
                </p> 
                    <br>
                <p><strong>Delete Your Personal Data:</strong></p> <p>
                  You have the right to delete or request that We assist in deleting the Personal Data that We have collected about You.
                  Our Service may give You the ability to delete certain information about You from within the Service.
                  You may update, amend, or delete Your information at any time by signing in to Your Account, if you have one, and visiting the account settings section that allows you to manage Your 
                  personal information. You may also contact Us to request access to, correct, or delete any personal information that You have provided to Us.
                  Please note, however, that We may need to retain certain information when we have a legal obligation or lawful basis to do so.
                </p>
                    <br>
                <p><strong>Security of Your Personal Data:</strong></p> <p>
                 I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over 
                 the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.
                </p>
                </p>
                    <br>
                <p><strong>Children’s Privacy:</strong></p> <p>
                 Our Service does not address anyone under the age of 13. We do not knowingly collect personally identifiable information from anyone under the age of 13. If You are a parent or guardian and 
                 You are aware that Your child has provided Us with Personal Data, please contact Us. If We become aware that We have collected Personal Data from anyone under the age of 13 without verification
                  of parental consent, We take steps to remove that information from Our servers. If We need to rely on consent as a legal basis for processing Your information and Your country requires 
                  consent from a parent, We may require Your parent's consent before We collect and use that information.
                </p>
                    <br>
                <p><strong>Changes to This Terms and Conditions</strong></p> <p>
                  I may update our Terms and Conditions from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Terms and
                  Conditions on this page.
                </p> 
                <p>
                  These terms and conditions are effective as of 2024-06-09
                </p> 
                    <br>
                <p><strong>Contact Us</strong></p> <p>
                  If you have any questions or suggestions about my Terms and Conditions, or Privacy Policy Of App,or to report any violations of the Policy or abuse of an Application, do not hesitate to 
                  contact us directly. We are also available to you in the case of requests for information, requests, or complaints at hariomyadav09062003@gmail.com
                  </p>  
                  <br>
                  <p>
                 By using the "DUAL Space", you acknowledge that you have read, understood, and agree to be bound by these terms and conditions. If you do not agree with any part of these terms and 
                 conditions, please do not use the App.
                  </p>            
    </body>
    </html>
"""
        // Replace this with your actual HTML content
        textViewHtml.text = HtmlCompat.fromHtml(htmlContent, HtmlCompat.FROM_HTML_MODE_COMPACT)

        // Enable accept button when both checkboxes are checked
        checkBox1.setOnCheckedChangeListener { _, _ ->
            updateAcceptButtonState(
                checkBox1,
                checkBox2,
                checkBox3,
                acceptButton
            )
        }
        checkBox2.setOnCheckedChangeListener { _, _ ->
            updateAcceptButtonState(
                checkBox1,
                checkBox2,
                checkBox3,
                acceptButton
            )
        }

        checkBox3.setOnCheckedChangeListener { _, _ ->
            updateAcceptButtonState(
                checkBox1,
                checkBox2,
                checkBox3,
                acceptButton
            )
        }

        // Handle accept button click
        acceptButton.setOnClickListener {
            // Do something when the user accepts the terms and conditions
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun updateAcceptButtonState(
        checkBox1: CheckBox,
        checkBox2: CheckBox,
        checkBox3: CheckBox,
        acceptButton: Button,
    ) {
        acceptButton.isEnabled = checkBox1.isChecked && checkBox2.isChecked && checkBox3.isChecked

    }
}