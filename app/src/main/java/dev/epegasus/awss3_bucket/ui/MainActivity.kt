package dev.epegasus.awss3_bucket.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import com.amplifyframework.storage.options.StoragePagedListOptions
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import dev.epegasus.awss3_bucket.R
import java.io.File

class MainActivity : AppCompatActivity() {

    private val textView: MaterialTextView by lazy { findViewById(R.id.mtv_title) }
    private val imageView: ShapeableImageView by lazy { findViewById(R.id.siv_image) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listFile()
        getFile()
    }

    private fun listFile() {
        val options = StoragePagedListOptions.builder()
            .setPageSize(1000)
            .build()

        Amplify.Storage.list("", options,
            { result ->
                Log.d(TAG, "Number of Items: ${result.items.size}")
                Log.d(TAG, "Next Token: ${result.nextToken}")
            },
            {
                Log.e(TAG, "List failure", it)
            }
        )
    }

    private fun getFile() {
        val key = "dp.PNG"

        val file = File("${applicationContext.cacheDir}/$key")
        if (file.exists()) {
            imageView.setImageURI(file.toUri())
            return
        }

        val options = StorageDownloadFileOptions.defaultInstance()
        Amplify.Storage.downloadFile(key, file, options,
            {
                val progress = (it.fractionCompleted * 100).toInt()
                textView.text = progress.toString()
            },
            {
                textView.text = "Success"
                val image = it.file.toUri()
                imageView.setImageURI(image)
            }, {
                textView.text = "Failure: ${it.message}"
            })
    }

    companion object {
        private const val TAG = "TAG_AWS"
    }
}