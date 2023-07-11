package hr.ivuksan.android.bikerepair.link.view

import hr.ivuksan.android.bikerepair.model.Repair

interface ILinkView {
    fun showRepairs(repairs: MutableList<Repair>)
    fun onLinkSuccess()
}