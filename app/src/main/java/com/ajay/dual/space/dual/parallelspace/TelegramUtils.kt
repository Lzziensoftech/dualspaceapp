package com.ajay.dual.space.dual.parallelspace

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.shashank.sony.fancytoastlib.FancyToast

object TelegramUtils {
    fun openTelegramToJoinGroup(context: Context) {
        val groupLink = ""
        // Create an Intent with the Telegram app's package name
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("org.telegram.messenger") // Use the appropriate package name
        // Set the data URI to the group link
        intent.data = Uri.parse(groupLink)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Handle the case when no activity can handle the intent
            FancyToast.makeText(
                context,
                "No app found to handle the Join action.",
                FancyToast.LENGTH_SHORT,
                FancyToast.WARNING,
                false
            ).show()
        }
    }
}