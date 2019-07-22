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
        Log.i("결과를 받긴 받았네", "$requestCode $resultCode")
        if (resultCode != Activity.RESULT_CANCELED) {

            Log.i("선택해볼까?", "$requestCode")
            if (resultCode == 3) {
                Log.i("사진을 찍어요", "$requestCode")
                captureFromCamera()
            }

            else if (resultCode == 4) {
                Log.i("갤러리를 찍어요", "$requestCode")
                pickFromGallery()
            }

            else if (requestCode == GALLERY_REQUEST_CODE) {
                //toast("사진 요청 완료")
                Log.i("사진 받아옴", "$requestCode")

                galleryImage(data)
            }

            else if (requestCode == CAMERA_REQUEST_CODE) {
                Log.i("사진 받아옴", cameraFilePath)
                    activity.testimage.setImageURI(Uri.parse(cameraFilePath));
            }


            Log.i("여기까지", "$requestCode $resultCode")

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
            Log.i("storage존재", imageFileName + " " + storageDir.absolutePath)
        }
        else {
            try {
                Log.i("storage없음", storageDir.absolutePath)
            }
            catch(ex: IOException) {
                Log.e("path.mkdirs", ex.toString())
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

            Log.i("여기까지", "왔네?")
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
        Log.i("사진을 골라주세요.","123")
        activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }
}