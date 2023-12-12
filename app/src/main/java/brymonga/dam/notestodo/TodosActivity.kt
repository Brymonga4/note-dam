package brymonga.dam.notestodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import brymonga.dam.notestodo.model.Category

class TodosActivity : AppCompatActivity() {

    private lateinit var selectedCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)
    }

        // TODO: Hacer el adapter y la lógica de manejo de tareas (añadir, eliminar, actualizar)
        //TODO: La vista de la actividad de Todos
}
