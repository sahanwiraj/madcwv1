package com.madcw.madcwv1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)


        // Simple Firebase read/write test
        val testRef = FirebaseDatabase.getInstance().getReference("testConnection")
        testRef.setValue("connected").addOnCompleteListener { writeTask ->
            if (writeTask.isSuccessful) {
                testRef.get().addOnSuccessListener { readSnapshot ->
                    val value = readSnapshot.getValue(String::class.java)
                    Toast.makeText(this, "Firebase test: $value", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Firebase read failed: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Firebase write failed: ${writeTask.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            } else {
                val database = FirebaseDatabase.getInstance()
                val usersRef = database.getReference("users")
                // Assume structure: users/{username}/password
                usersRef.child(username).child("password").get().addOnSuccessListener { dataSnapshot ->
                    val dbPassword = dataSnapshot.getValue(String::class.java)
                    if (dbPassword != null && dbPassword == password) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Database error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
