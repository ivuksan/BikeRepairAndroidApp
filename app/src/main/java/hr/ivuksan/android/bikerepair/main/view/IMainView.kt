package hr.ivuksan.android.bikerepair.main.view

import hr.ivuksan.android.bikerepair.model.Bicycle

interface IMainView {
    fun showBicycles(bicycles: MutableList<Bicycle>)
}