package hr.ivuksan.android.bikerepair.insert.controller

interface IAddController {
    fun onNewBicycle(owner: String, brand: String, name: String, category: String)
}