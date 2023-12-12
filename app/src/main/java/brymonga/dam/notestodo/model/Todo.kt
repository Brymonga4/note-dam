package brymonga.dam.notestodo.model

import java.time.LocalDate

data class ToDo(
    val isDone: Boolean,
    var fechaCreacion: LocalDate,
    var contenido: String,
    var listSubElemento: List <SubElemento>,
    var categoria: Category
)