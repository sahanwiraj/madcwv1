package com.madcw.madcwv1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var btnAdminRole: Button
    lateinit var btnTeacherRole: Button
    lateinit var btnStudentRole: Button

    public var curruntRole: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAdminRole = findViewById(R.id.btnAdminRole)
        btnTeacherRole = findViewById(R.id.btnTeacherRole)
        btnStudentRole = findViewById(R.id.btnStudentRole)

        btnAdminRole.setOnClickListener{
            Toast.makeText(this, "Admin role selected", Toast.LENGTH_SHORT).show()
            curruntRole = 1

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnTeacherRole.setOnClickListener{
            Toast.makeText(this, "Teacher role selected", Toast.LENGTH_SHORT).show()
            curruntRole = 2

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnStudentRole.setOnClickListener{
            Toast.makeText(this, "Student role selected", Toast.LENGTH_SHORT).show()
            curruntRole = 3

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}