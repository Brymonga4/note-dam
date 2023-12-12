package brymonga.dam.notestodo.model

import java.time.LocalDate


data class Category(
    val name: String,
    val creationDate: LocalDate,
    val isSelected: Boolean,
    val elementos: List<ToDo>
)





