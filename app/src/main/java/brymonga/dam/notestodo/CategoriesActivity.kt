package brymonga.dam.notestodo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import brymonga.dam.notestodo.manejodatos.CsvUtils
import brymonga.dam.notestodo.manejodatos.JsonUtils


class CategoriesActivity : AppCompatActivity() {

    private lateinit var fileType: FileType

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        fileType = intent.getStringExtra(FILE_TYPE_EXTRA)?.let { FileType.valueOf(it) }
            ?: throw IllegalArgumentException("File type not provided")


        val categories = when (fileType) {
            FileType.CSV -> CsvUtils.readCategoriesFromCsv()
            FileType.JSON -> JsonUtils.readCategoriesFromJson()
        }

        // TODO: Hacer el layout de las categorias
    }
}
