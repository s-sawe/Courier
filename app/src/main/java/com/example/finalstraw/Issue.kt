package com.example.finalstraw

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalstraw.databinding.ActivityIssueBinding
import java.net.URI
import android.app.ProgressDialog
import android.provider.MediaStore
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.storage.FirebaseStorage

class Issue : AppCompatActivity() {

    lateinit var binding: ActivityIssueBinding
    lateinit var ImageUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.select.setOnClickListener{

            selectImage()
        }
        binding.upload.setOnClickListener{

            uploadImage()
        }

    }

    private fun uploadImage() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading Photo...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_mm_dd_HH_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(ImageUri)
            .addOnSuccessListener{
                binding.firebaseImage.setImageURI(null)
                Toast.makeText(this, "Image Upload Successful", Toast.LENGTH_SHORT).show()
                if(progressDialog.isShowing)progressDialog.dismiss()

            }.addOnFailureListener{
                if(progressDialog.isShowing)progressDialog.dismiss()
                Toast.makeText(this, "Image Upload Failed", Toast.LENGTH_SHORT).show()
            }
    

    }

    private fun selectImage() {

        val intent = Intent()
        intent.type = "image/*"
        intent.setAction(Intent.ACTION_GET_CONTENT)

        startActivityForResult(intent, 100)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){

            ImageUri = data?.data!!
            binding.firebaseImage.setImageURI(ImageUri)
        }
    }


}