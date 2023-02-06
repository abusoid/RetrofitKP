package com.example.retrofitkp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.retrofitkp.views.startPage.StartFragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.nav_host,
            StartFragment.newInstance(),
            "startFragment"
        ).commit()
    }
}