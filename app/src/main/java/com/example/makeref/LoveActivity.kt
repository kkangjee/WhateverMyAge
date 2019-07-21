package com.example.makeref

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love.*
import kotlinx.android.synthetic.main.lovearticles.*
import android.location.Geocoder
import android.location.GpsSatellite
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.getSystemService
import android.location.LocationManager
import android.location.Criteria
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest
import androidx.core.content.ContextCompat.getSystemService
import android.text.TextUtils
import android.provider.Settings.Secure
import android.provider.Settings.Secure.LOCATION_MODE_OFF
import android.provider.Settings.SettingNotFoundException
import android.provider.Settings.Secure.LOCATION_MODE
import android.os.Build


fun isLocationEnabled(context: Context): Boolean {
    var locationMode = 0
    val locationProviders: String

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        try {
            locationMode = android.provider.Settings.Secure.getInt(context.contentResolver, android.provider.Settings.Secure.LOCATION_MODE)

        } catch (e: SettingNotFoundException) {
            e.printStackTrace()
            return false
        }

        return locationMode != android.provider.Settings.Secure.LOCATION_MODE_OFF

    } else {
        locationProviders =
            android.provider.Settings.Secure.getString(context.contentResolver, android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
        return !TextUtils.isEmpty(locationProviders)
    }


}


class LoveActivity : AppCompatActivity() {
    var contentlist = arrayListOf(
        LoveArticles("story1", "sarangbang", "오늘은 여기에 놀러 갔어요", "3", "5"),
        LoveArticles("story2", "whats wrong", "멋쟁이 사자처럼 화이팅", "32", "6"),
        LoveArticles("story3", "with my age", "내 나이가 어때서", "9", "5"),
        LoveArticles("story4", "lets do this", "ㅎㅎㅎ", "7", "15")
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love)

        val locationMgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_COARSE
        criteria.powerRequirement = Criteria.POWER_LOW
        criteria.isAltitudeRequired = false
        criteria.isBearingRequired = false
        criteria.isSpeedRequired = false
        criteria.isCostAllowed = true

        //val gpsEnabled = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED)

        /*

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );
        }*/
        //val mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        val love = LoveArticlesAdapter(this, contentlist)
        lovearticles.adapter = love

        val lm = LinearLayoutManager(this)
        lovearticles.layoutManager = lm
        lovearticles.setHasFixedSize(true)

        bt_writeArticle.setOnClickListener {//setting 화면
            val intent = Intent(this, LoveWriteArticle::class.java)
            intent.putExtra("QuestionAnswerArticle", 2)  //게시글 쓰기
            startActivity(intent)
        }

        bt_back.setOnClickListener{
            finish()

        }

        bt_locationselect.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // No one provider activated: prompt GPS
                /* if (mProviderName == null || mProviderName.equals("")) {
                     startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));*/
                toast("위치 권한을 허용하지 않으면 지역별 친구 찾기를 할 수 없어요.")
                val permission_list = arrayOf(
                    //android.Manifest.permission.WRITE_CONTACTS,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )

                requestPermissions(permission_list, 0)
                //, android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
            else {
                if (!isLocationEnabled(this)) {
                    toast("위치 사용을 켜주세요.")
                    val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivityForResult(intent, 0)
                } else {
                    val bestProvider = locationMgr.getBestProvider(criteria, true)

                    val gps = locationMgr.getLastKnownLocation(bestProvider)

                    val lat = gps.getLatitude()
                    val lng = gps.getLongitude()
                    val gcd = Geocoder(this, Locale.getDefault())
                    val addresses = gcd.getFromLocation(lat, lng, 1)

                    val cityName = addresses[0].getAddressLine(0)
                    val stateName = addresses[0].getAddressLine(1)
                    val countryName = addresses[0].getAddressLine(2)

                    toast(cityName)
                }
            }
        }

    }




}