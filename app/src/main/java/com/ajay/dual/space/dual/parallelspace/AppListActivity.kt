package com.ajay.dual.space.dual.parallelspace

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajay.dual.space.dual.parallelspace.databinding.ActivityAppListBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppListBinding
    private lateinit var loadingDialog: LoadingDialog

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppListBinding.inflate(layoutInflater)
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
        loadAppList()
        // Initialize ProgressBar
        loadingDialog.show()
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            loadingDialog.dismiss()
//            if (FacebookAdsManager.isInterstitialAdLoaded()) {
//                FacebookAdsManager.showInterstitialAd(this@AppListActivity)
//            } else if (AdManager.isInterstitialAdLoaded()) {
//                AdManager.showInterstitialAd(this@AppListActivity)
//            } else {
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Google Admob ad is closed
//            AdManager.performActionWhenAdClosed(this@AppListActivity) {
//                loadingDialog.dismiss()
//            }
//            // Perform the task after the Facebook ad is closed
//            FacebookAdsManager.performActionWhenAdClosed {
//                loadingDialog.dismiss()
//            }
        }

        binding.cloneButton.setOnClickListener {
            loadingDialog.show()
            GlobalScope.launch(Dispatchers.Main) {
                delay(1000)
                cloneSelectedAppsAndStartFinalClonedActivity()
//                if (FacebookAdsManager.isInterstitialAdLoaded()) {
//                    FacebookAdsManager.showInterstitialAd(this@AppListActivity)
//                } else if (AdManager.isInterstitialAdLoaded()) {
//                    AdManager.showInterstitialAd(this@AppListActivity)
//                } else {
//                    cloneSelectedAppsAndStartFinalClonedActivity()
//                }
//                // Perform the task after the Google Admob ad is closed
//                AdManager.performActionWhenAdClosed(this@AppListActivity) {
//                    cloneSelectedAppsAndStartFinalClonedActivity()
//                }
//                // Perform the task after the Facebook ad is closed
//                FacebookAdsManager.performActionWhenAdClosed {
//                    cloneSelectedAppsAndStartFinalClonedActivity()
//                }
            }
        }
    }

    private fun loadAppList() {
        val packageManager = packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        // Filter out system apps
        val userApps = apps.filter { it.flags and ApplicationInfo.FLAG_SYSTEM == 0 }.map {
            val drawable = it.loadIcon(packageManager)
            val bitmap = if (drawable is BitmapDrawable) {
                drawable.bitmap
            } else {
                // Convert Drawable to Bitmap
                val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                bitmap
            }
            AppInfo(
                it.loadLabel(packageManager).toString(),
                it.packageName,
                bitmap // Pass Bitmap instead of Drawable
            )
        }

        // Set up RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = AppListAdapter(userApps, { app ->
            // Handle app selection
            Toast.makeText(this, "Selected app: ${app.name}", Toast.LENGTH_SHORT).show()
        }, { selectedCount ->
            // Update selected count TextView
            binding.selectedCountTextView.text = "Selected apps: $selectedCount"
            // Enable or disable the button based on selection count
            binding.cloneButton.isEnabled = selectedCount > 0
        })
    }

    private fun cloneSelectedAppsAndStartFinalClonedActivity() {
        val intent = Intent(this, cloiningappactivity::class.java)
        // Hide loading indicator after the task is performed
        loadingDialog.dismiss()
        startActivity(intent)
        finish()
    }

    private fun bannerAdsAdmob() {
        FacebookAdsManager.loadBannerAd(
            this,
            getString(R.string.facebook_Banner),
            binding.bannerContainer
        )
        AdManager.loadBannerAd(this, binding.adView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}