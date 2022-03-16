package com.example.appcocina.data.database.entidades

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class Recipes_User_Relation(
    @Embedded val recipes: Recipes,
    @Relation(
        parentColumn = "id_Recipes",
        entityColumn = "id_User",
        associateBy = Junction(Recipes_User_CroosRef::class)
    )
    val songs: List<User>
)

data class User_Recipes_Relation(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id_USer",
        entityColumn = "id_Recipes",
        associateBy = Junction(Recipes_User_CroosRef::class)
    )
    val playlists: List<Recipes>
)