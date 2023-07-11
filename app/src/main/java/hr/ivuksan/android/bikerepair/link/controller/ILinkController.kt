package hr.ivuksan.android.bikerepair.link.controller

import hr.ivuksan.android.bikerepair.model.Repair

interface ILinkController {
    fun onLoad()
    fun addRepairToBicycle(repair: Repair)
}