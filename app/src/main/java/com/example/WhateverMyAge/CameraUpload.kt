package com.example.WhateverMyAge

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_love_write_article.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

const val GALLERY_REQUEST_CODE = 1
const val CAMERA_REQUEST_CODE = 2
var cameraFilePath: String? = null

class CameraUpload (activity : Activity){

    val activity = activity

    fun newOnActivityResult (requestCode : Int, resultCode : Int, data: Intent?) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (resultCode == 3) {
                captureFromCamera()
            } else if (resultCode == 4) {
                pickFromGallery()
            } else if (requestCode == GALLERY_REQUEST_CODE) {
                //toast("사진 요청 완료")
                galleryImage(data)
            } else if (requestCode == CAMERA_REQUEST_CODE) {
                activity.testimage.setImageURI(Uri.parse(cameraFilePath));
            }
        }
    }

    fun galleryImage(data: Intent?) {
        val selectedImage = data!!.data as Uri
        //testimage.setImageURI(selectedImage)

        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        // Get the cursor
        val cursor = activity.contentResolver.query(selectedImage, filePathColumn, null, null, null) as Cursor
        // Move to first row
        cursor.moveToFirst()
        //Get the column index of MediaStore.Images.Media.DATA
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        //Gets the String value in the column
        val imgDecodableString = cursor.getString(columnIndex)
        activity.toast(imgDecodableString)
        cursor.close()
        // Set the Image in ImageView after decoding the String
        activity.testimage.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString))
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        //This is the directory in which the file will be created. This is the default location of Camera photos
        val storageDir = File(Environment.getExternalStorageDirectory().getAbsolutePath(), "DCIM")

        if (storageDir.exists()) {
        }
        else {
            try {
            }
            catch(ex: IOException) {
            }
        }

        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )

        // Save a file: path for using again
        cameraFilePath = "file://" + image.absolutePath
        return image
    }

    fun captureFromCamera() {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", createImageFile()))
            activity.startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }

        catch (ex: IOException) {
            ex.printStackTrace()
        }

    }

    fun pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        val intent =  Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*")
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes)
        // Launching the Intent
        activity.toast("사진을 골라주세요.")
        activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }
}