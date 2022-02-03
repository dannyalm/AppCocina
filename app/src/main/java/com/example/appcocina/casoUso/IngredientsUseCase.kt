package com.example.appcocina.casoUso

import com.example.appcocina.entities.Ingredients

class IngredientsUseCase {

    private val ingredientsList = listOf<Ingredients>(
        Ingredients(
            1,
            "Arroz",
        "https://s1.eestatic.com/2021/05/31/actualidad/585453954_186766988_1706x960.jpg"
        ),
        Ingredients(
            2,
            "Carne",
            "https://st.depositphotos.com/1049691/2348/i/600/depositphotos_23480847-stock-photo-raw-beef.jpg"
        ),
        Ingredients(
            3,
            "Pollo",
            "https://thumbs.dreamstime.com/b/prendedero-crudo-de-la-pechuga-pollo-aislado-en-blanco-102486697.jpg"
        ),
        Ingredients(
            4,
            "Huevo",
            "https://i.blogs.es/09c069/huevo/450_1000.jpg"
        ),
        Ingredients(
            5,
            "Tomate",
            "https://st2.depositphotos.com/1001348/8036/i/600/depositphotos_80369462-stock-photo-red-tomatoes-with-cut-isolated.jpg"
        ),
    )

    fun getAllIngredients():List<Ingredients>{
        return ingredientsList
    }
}