package hr.ivuksan.android.bikerepair.detail.view

import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.model.Repair

interface IDetailView {
    fun showImage(imageName: String)
    fun showBicycleDetails(bicycle: Bicycle)
    fun showBicycleRepairs(repairs: MutableList<Repair>)
}