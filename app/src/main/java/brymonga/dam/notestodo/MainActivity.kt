package brymonga.dam.notestodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import brymonga.dam.notestodo.model.Category


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        csvButton.setOnClickListener {
            navigateToCategoriesActivity(FileType.CSV)
        }

        jsonButton.setOnClickListener {
            navigateToCategoriesActivity(FileType.JSON)
        }


    }

}


const val FILE_TYPE_EXTRA = "fileType"
enum class FileType { CSV, JSON }
