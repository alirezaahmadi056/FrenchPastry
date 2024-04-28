package info.alirezaahmadi.frenchpastry.ui.activity

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.CakeApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.databinding.ActivityCustomCakeBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.io.InputStream

class CustomCakeActivity : AppCompatActivity(), CallbackRequest<DefaultModel> {

    private lateinit var binding: ActivityCustomCakeBinding
    private var uriState = false
    private lateinit var baseUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomCakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customAppBar.showNavDrawer(this)

        binding.customAppBar.getBackIcon().setOnClickListener {
            finish()
        }

        binding.viewAddImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.viewPlus.setOnClickListener {

            var count = binding.txtCount.text.toString().toInt()

            if (count < 5)
                count++

            binding.txtCount.text = count.toString()

        }

        binding.viewMin.setOnClickListener {

            var count = binding.txtCount.text.toString().toInt()

            if (count > 1)
                count--

            binding.txtCount.text = count.toString()

        }

        binding.viewPlus2.setOnClickListener {

            var count = binding.txtCountRate.text.toString().toInt()

            if (count < 3)
                count++

            binding.txtCountRate.text = count.toString()

        }

        binding.viewMin2.setOnClickListener {

            var count = binding.txtCountRate.text.toString().toInt()

            if (count > 1)
                count--

            binding.txtCountRate.text = count.toString()

        }

        binding.btnConfirm.getView().setOnClickListener {

            binding.btnConfirm.enableProgress()
            val text = binding.edtComment.text.toString()
            val weight = binding.txtCount.text.toString()
            val floor = binding.txtCountRate.text.toString()

            if (text.isNotEmpty() && text.length > 10) {
                if (uriState) {
                    try {
                        CakeApiRepository.instance.sendCake(
                            this,
                            DeviceInfo.getApi(this),
                            DeviceInfo.getDeviceID(this),
                            DeviceInfo.getPublicKey(this),
                            listOf(uriToFile(baseUri)!!),
                            RequestBody.create(MediaType.parse("text/plain"), text),
                            RequestBody.create(MediaType.parse("text/plain"), weight),
                            RequestBody.create(MediaType.parse("text/plain"), floor)
                        )
                    } catch (e: Exception) {
                        ToastUtils.toast(this, "خطا در بارگذاری تصویر")
                        Log.i("TEST_IMAGE", e.message.toString())
                    }
                } else
                    Toast.makeText(this, "لطفا تصویر را وارد کنید", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "لطفا توضیحات کاملی وارد کنید", Toast.LENGTH_SHORT).show()

        }

    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                if (checkImageSize(uri, contentResolver)) {
                    uriState = true
                    baseUri = uri
                    binding.imgCake.setImageURI(uri)
                }
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

    private fun uriToFile(uri: Uri): File? {

        val inputStream: InputStream? = contentResolver.openInputStream(uri)

        val fileExtension: String? = when (contentResolver.getType(uri)) {
            "image/jpeg" -> "jpg"
            "image/png" -> "png"
            else -> null // پسوند معتبر برای تصویر مشخص نشده است
        }

        if (fileExtension != null) {
            val tempFile = File.createTempFile("image", ".$fileExtension")
            inputStream?.copyTo(tempFile.outputStream())
            return tempFile
        }

        inputStream?.close()

        return null

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

    //برای اینکه فقط jpg رو قبول کنه
    /*fun uriToFile(uri: Uri): File {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("image", ".jpg")
        inputStream?.copyTo(tempFile.outputStream())
        inputStream?.close()
        return tempFile
    }*/

    //برای اینکه هر پسوندی رو ساپورت کنیم
    /*fun uriToFile(contentResolver: ContentResolver, uri: Uri): File? {

        val inputStream: InputStream? = contentResolver.openInputStream(uri)

        val fileName = uri.lastPathSegment
        val fileExtension = fileName?.substringAfterLast('.', "")

        if (fileExtension != null) {
            val tempFile = File.createTempFile("image", ".$fileExtension")
            inputStream?.copyTo(tempFile.outputStream())
            return tempFile
        }

        inputStream?.close()

        return null
    }*/

    override fun onSuccess(code: Int, data: DefaultModel) {
        ToastUtils.toast(this, data.message)
        binding.btnConfirm.disableProgress()
    }

    override fun onNotSuccess(code: Int, error: String) {
        ToastUtils.toast(this, error)
        binding.btnConfirm.disableProgress()
    }

    override fun onError(error: String) {
        ToastUtils.toastServerError(this)
        binding.btnConfirm.disableProgress()
    }

}