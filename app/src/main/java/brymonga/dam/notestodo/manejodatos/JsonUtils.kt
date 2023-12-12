package brymonga.dam.notestodo.manejodatos

import brymonga.dam.notestodo.model.Category
import brymonga.dam.notestodo.model.SubElemento
import brymonga.dam.notestodo.model.ToDo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object JsonUtils {

    private const val JSON_FILE_PATH = "categories.json"

    private val json = Json { prettyPrint = true }

    fun writeCategoriesToJson(categories: List<Category>) {
        val jsonString = json.encodeToString(categories)
        File(JSON_FILE_PATH).writeText(jsonString)
    }

    fun readCategoriesFromJson(): List<Category> {
        val jsonString = File(JSON_FILE_PATH).readText()
        val categories = json.decodeFromString<List<Category>>(jsonString)

        return categories.map { category ->
            Category(
                name = category.name,
                creationDate = category.creationDate,
                isSelected = category.isSelected,
                elementos = category.elementos.map { toDo ->
                    ToDo(
                        isDone = toDo.isDone,
                        fechaCreacion = toDo.fechaCreacion,
                        contenido = toDo.contenido,
                        listSubElemento = toDo.listSubElemento.map { subElemento ->
                            SubElemento(
                                isDone = subElemento.isDone,
                                contenido = subElemento.contenido
                            )
                        },
                        categoria = category
                    )
                }.toMutableList()
            )
        }
    }
}
