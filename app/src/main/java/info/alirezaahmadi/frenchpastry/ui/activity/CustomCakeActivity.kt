package info.alirezaahmadi.frenchpastry.ui.activity

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.databinding.ActivityCustomCakeBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class CustomCakeActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var binding: ActivityCustomCakeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomCakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewAddImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                if (checkImageSize(uri, contentResolver))
                    binding.imgCake.setImageURI(uri)
            }
        }

    private fun checkImageSize(imageUri: Uri, contentResolver: ContentResolver): Boolean {

        try {
            val inputStream = contentResolver.openInputStream(imageUri)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()

            val imageWidth = options.outWidth
            val imageHeight = options.outHeight
            val imageSize = imageWidth * imageHeight // حجم تقریبی تصویر (بدون فشرده‌سازی)

            Log.d("CheckImageSize", "Image Width: $imageWidth pixels")
            Log.d("CheckImageSize", "Image Height: $imageHeight pixels")
            Log.d("CheckImageSize", "Image Size: $imageSize bytes")

            if (imageSize > 9_000_000) {
                ToastUtils.toast(this, "از تصاویر با سایز کوچک تر استفاده کنید")
                return false
            }

            return true

        } catch (e: Exception) {
            Log.e("CheckImageSize", "Error occurred while checking image size: ${e.message}")
            return false
        }

    }

    /*fun checkImageDimensions(imageUri: Uri, maxWidth: Int, maxHeight: Int): Boolean {

        val inputStream = context.contentResolver.openInputStream(imageUri)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(inputStream, null, options)

        inputStream?.close()

        val imageWidth = options.outWidth
        val imageHeight = options.outHeight

        if (imageWidth > maxWidth || imageHeight > maxHeight) {
            Toast.makeText(this, imageWidth.toString(), Toast.LENGTH_SHORT).show()
            return false
        }

        return true

    }*/

}