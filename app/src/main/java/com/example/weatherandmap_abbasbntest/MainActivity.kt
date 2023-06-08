package com.example.weatherandmap_abbasbntest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import coil.load
import com.example.weatherandmap_abbasbntest.databinding.ActivityMainBinding
import com.example.weatherandmap_abbasbntest.ui.LocationPickerDialog
import com.example.weatherandmap_abbasbntest.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LocationPickerDialog.OnLocationSelectedListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var queries: HashMap<String, String>
    private val viewModel: HomeViewModel by viewModels()
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val RC_LOCATION = 28

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setContentView(binding.root)
        binding.btsearch.isVisible = false

        initmaplisteners()
        initlisteners()

    }


    fun initlisteners() {
        binding.btsearch.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermissions()) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ),
                        RC_LOCATION
                    )
                } else {
                    showMapDialog()
                }
            } else {
                showMapDialog()
            }
        }



    }
    fun initmaplisteners() {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermissions()) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ),
                        RC_LOCATION
                    )
                } else {
                    showMapDialog()
                }
            } else {
                showMapDialog()
            }

    }

    private fun showMapDialog() {
        LocationPickerDialog().show(
            supportFragmentManager, "TAG_LOCATION"
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RC_LOCATION -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMapDialog()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Location permission required",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    private fun checkPermissions(): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun onLocationSelected(lat: Double, lng: Double) {
        Toast.makeText(this, "لطفا صبر کنید اینترنت کند است", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "دما بر اساس سلسیوس C°است ", Toast.LENGTH_LONG).show()

        queries = HashMap()
        queries[com.example.weatherandmap_abbasbntest.utils.Constants.API_KEY] =
            com.example.weatherandmap_abbasbntest.utils.Constants.MY_API_KEY
        /*queries["lat"] = "35.6944"
        queries["lon"] = "51.4215"*/
        queries["lat"] = "${lat}"
        queries["lon"] = "${lng}"
        queries["lang"] = "fa"
        queries["units"] = "metric"

        viewModel.loadTopWeatherList(queries)


        binding.apply {
            //Get Weather
            viewModel.topWeatherList.observe(this@MainActivity) {
                var stu = it.weather[0]
                var img = stu.icon
                var coilimg = "${img}@2x.png"

                textstatus.text = "" + stu.description
                temp.text = "دما:" + it.main.temp.toString() + "\n" +
                        "احساس واقعی از دما:" + it.main.feelsLike.toString() + "\n" +
                        " حداقل دما:" + it.main.tempMin.toString() + "\n" +
                        "حداکثر دما:" + it.main.tempMax.toString() + "\n" +
                        "فشار هوا : " + it.main.pressure.toString() + "\n" +
                        "رطوبت : " + it.main.humidity.toString()
                nameofcity.text = "" + it.name
                imageicon.load("http" + "://openweathermap.org/img/wn/" + coilimg)


            }


        }

        binding.btsearch.isVisible = true

    }


}