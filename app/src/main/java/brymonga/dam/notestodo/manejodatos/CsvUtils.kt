package brymonga.dam.notestodo.manejodatos

import android.os.Build
import androidx.annotation.RequiresApi
import brymonga.dam.notestodo.model.Category
import brymonga.dam.notestodo.model.SubElemento
import brymonga.dam.notestodo.model.ToDo
import com.google.android.libraries.places.api.model.LocalDate
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import java.io.FileReader
import java.io.FileWriter


object CsvUtils {

    private const val CSV_FILE_PATH = "categories.csv"

    fun writeCategoriesToCsv(categories: List<Category>) {
        val csvWriter = CSVWriter(FileWriter(CSV_FILE_PATH))

        csvWriter.writeNext(arrayOf("Category Name", "Creation Date", "Selected", "ToDo Done", "ToDo Creation Date", "ToDo Content", "SubElemento Done", "SubElemento Content"))

        categories.forEach { category ->
            category.elementos.forEach { toDo ->
                toDo.listSubElemento.forEach { subElemento ->
                    csvWriter.writeNext(
                        arrayOf(
                            category.name,
                            category.creationDate.toString(),
                            category.isSelected.toString(),
                            toDo.isDone.toString(),
                            toDo.fechaCreacion.toString(),
                            toDo.contenido,
                            subElemento.isDone.toString(),
                            subElemento.contenido
                        )
                    )
                }
            }
        }

        csvWriter.close()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readCategoriesFromCsv(): List<Category> {
        val categories = mutableListOf<Category>()
        val csvReader = CSVReader(FileReader(CSV_FILE_PATH))

        // Ignorar la primera línea (encabezados)
        csvReader.skip(1)

        val tempCategoriesMap = mutableMapOf<String, Category>()


        var nextRecord: Array<String>?
        while (csvReader.readNext().also { nextRecord = it } != null) {
            val categoryName = nextRecord!![0]
            val creationDate = LocalDate.parse(nextRecord!![1])
            val isSelected = nextRecord!![2].toBoolean()
            val order = nextRecord!![3].toInt()

            val category = tempCategoriesMap.getOrPut(categoryName) {
                Category(categoryName, creationDate, isSelected, mutableListOf())
            }

            // Leer datos del ToDo y subelemento
            val toDo = ToDo(
                isDone = nextRecord!![4].toBoolean(),
                fechaCreacion = LocalDate.parse(nextRecord!![5]),
                contenido = nextRecord!![6],
                listSubElemento = mutableListOf(),
                categoria = category
            )


            if (nextRecord!!.size > 7 && nextRecord!![7].isNotEmpty()) {
                val subElemento = SubElemento(
                    isDone = nextRecord!![7].toBoolean(),
                    contenido = nextRecord!![8]
                )
                toDo.listSubElemento.add(subElemento)
            }

            category.elementos.add(toDo)
        }


        // TODO: Que funcionen las fechas
        csvReader.close()


        categories.addAll(tempCategoriesMap.values)

        return categories
    }
}
