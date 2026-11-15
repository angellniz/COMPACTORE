package com.example.compactore1

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.io.ByteArrayOutputStream
import android.graphics.Bitmap



class ProfileFragment : Fragment() {

    lateinit var welcomeText: TextView
    lateinit var loginBtn: Button
    lateinit var signupBtn: Button
    lateinit var profileImage: ImageView
    lateinit var logoutBtn: Button

    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        welcomeText = view.findViewById(R.id.welcomeText)
        loginBtn = view.findViewById(R.id.loginButton)
        signupBtn = view.findViewById(R.id.signupButton)
        profileImage = view.findViewById(R.id.profileImage)
        logoutBtn = view.findViewById(R.id.logoutButton)

        sharedPref = requireActivity().getSharedPreferences("UserData", 0)

        val username = sharedPref.getString("username", "User")
        val loggedIn = sharedPref.getBoolean("loggedIn", false)

        // Load saved profile image
        val savedImage = sharedPref.getString("profileImage", null)

        if (savedImage != null) {

            val bytes = Base64.decode(savedImage, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

            profileImage.setImageBitmap(bitmap)

        }

        // Welcome text
        if (loggedIn) {

            welcomeText.text = "Welcome, $username!"

            loginBtn.visibility = View.GONE
            signupBtn.visibility = View.GONE

        }

        // Open login page
        loginBtn.setOnClickListener {

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

        }

        // Change profile picture
        profileImage.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            startActivityForResult(intent, 100)

        }

        // Logout logic
        logoutBtn.setOnClickListener {

            val editor = sharedPref.edit()

            editor.clear()
            editor.apply()

            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment())
                .commit()

        }

        // Show logout only when logged in
        if (loggedIn) {
            logoutBtn.visibility = View.VISIBLE
        } else {
            logoutBtn.visibility = View.GONE
        }

        return view
    }

    // Image picker result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {

            val imageUri = data?.data

            val inputStream = requireActivity()
                .contentResolver.openInputStream(imageUri!!)

            val bitmap = BitmapFactory.decodeStream(inputStream)

            profileImage.setImageBitmap(bitmap)

            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

            val bytes = stream.toByteArray()

            val encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT)

            val editor = sharedPref.edit()

            editor.putString("profileImage", encodedImage)
            editor.apply()

        }
    }
}