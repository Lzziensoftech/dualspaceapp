package com.ajay.dual.space.dual.parallelspace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppListAdapter(
    val apps: List<AppInfo>,
    private val onItemClick: (AppInfo) -> Unit,
    private val onSelectionChange: (Int) -> Unit,
) : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val appIcon: ImageView = itemView.findViewById(R.id.appIcon)
        private val appName: TextView = itemView.findViewById(R.id.appName)
        private val appPackage: TextView = itemView.findViewById(R.id.appPackage)
        private val appCheckBox: CheckBox = itemView.findViewById(R.id.appCheckBox)

        // In your AppViewHolder, set the ImageView's image using the resource ID
        // In your AppViewHolder, set the ImageView's image using the bitmap directly
        fun bind(app: AppInfo) {
            appIcon.setImageBitmap(app.icon) // Set bitmap directly
            appName.text = app.name
            appPackage.text = app.packageName
            appCheckBox.isChecked = app.isSelected

            // Handle item click
            itemView.setOnClickListener {
                app.isSelected = !app.isSelected
                appCheckBox.isChecked = app.isSelected
                onItemClick(app)
                onSelectionChange(apps.count { it.isSelected })
            }

            // Handle checkbox click
            appCheckBox.setOnClickListener {
                app.isSelected = appCheckBox.isChecked
                onSelectionChange(apps.count { it.isSelected })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(apps[position])
    }

    override fun getItemCount(): Int {
        return apps.size
    }
}