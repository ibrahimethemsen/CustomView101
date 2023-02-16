package com.ibrahimethemsen.customview101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibrahimethemsen.customview101.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.avatarView.setAvatar(Avatar(avatarImage = R.drawable.ic_launcher_foreground))

    }
}