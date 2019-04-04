package com.boulevard.uitestingarticle.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.boulevard.uitestingarticle.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PlantListFragment())
            .commit()
    }
}
