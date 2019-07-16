package com.example.makeref

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_love_write_article.*
import java.io.File
import android.R.attr.data
import android.R.attr.data
import android.database.Cursor
import android.graphics.BitmapFactory
import android.os.Environment
import android.widget.ImageView
import android.provider.MediaStore
import android.os.Environment.DIRECTORY_DCIM
import android.os.Environment.getExternalStoragePublicDirectory
import android.util.Log
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class LoveWriteArticle : AppCompatActivity() {
    //TODO : CameraUpload 클래스랑 합치기....
    override fun onActivityResult (requestCode : Int, resultCode : Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                toast("사진 요청 완료")
                val selectedImage = data!!.data as Uri
                //testimage.setImageURI(selectedImage)


                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                // Get the cursor
                val cursor = contentResolver.query(selectedImage, filePathColumn, null, null, null) as Cursor
                // Move to first row
                cursor.moveToFirst()
                //Get the column index of MediaStore.Images.Media.DATA
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                //Gets the String value in the column
                val imgDecodableString = cursor.getString(columnIndex)
                toast(imgDecodableString)
                cursor.close()
                // Set the Image in ImageView after decoding the String
                testimage.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString))
            }

            else if (requestCode == CAMERA_REQUEST_CODE) {
                Log.i("사진 받아옴", cameraFilePath)
                testimage.setImageURI(Uri.parse(cameraFilePath));
            }

        }
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
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()))

            Log.i("여기까지", "왔네?")
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }

        catch (ex: IOException) {
            ex.printStackTrace()
        }

    }

    fun pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        val intent =  Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        toast("사진을 골라주세요.")
        startActivityForResult(intent, 1);
    }
    //TODO : 요기까지가 중복...


/*            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", createImageFile()))*/

    val camera = CameraUpload()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love_write_article)
        val cora = intent.getIntExtra("CommentOrArticle", -1)
        if (cora == 1) {
            //게시글에 등록
        }

        else if (cora == 0) {
            //댓글에 등록
            
        }

        lovearticlespicupload.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //}
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 0)

                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    //if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    val intent = Intent(this, AddcontactActivity::class.java)
                    startActivity(intent)
                    //}
                    //ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 0)
                }
                else {
                    toast("사진을 업로드 하려면 권한이 필요해요.")
                    finish()
                }
            }
            
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)

                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        //CameraUpload.captureFromCamera()
                    captureFromCamera()
                }
                else {
                    toast("카메라로 업로드 하려면 권한이 필요해요.")
                    finish()
                }
            }
            
            
            else {
                //pickFromGallery()
                captureFromCamera()
            }

        }

    }
}


/*
*   val context = this
                val intent = Intent(android.content.Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                intent.putExtra(
                    Intent.EXTRA_STREAM,
                    FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, File(filePath))
                )
                val chooser = Intent.createChooser(intent, context.getString(android.R.string.share))
                context.startActivity(chooser)
*
* */