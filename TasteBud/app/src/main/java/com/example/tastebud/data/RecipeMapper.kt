package com.example.tastebud.data

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.screens.home.HomeContent
import com.google.firebase.firestore.QuerySnapshot

@Composable
fun RecipeMapper(data: QuerySnapshot) {
    val image = data.image
    {
        image =
            https://img.spoonacular.com/recipes/798400-312x231.jpg, analyzedInstructions=[{number=1, ingredients=[{image=https://spoonacular.com/cdn/ingredients_100x100/water.png, localizedName=water, name=water, id=14412}, {image=https://spoonacular.com/cdn/ingredients_100x100/peas.jpg, localizedName=peas, name=peas, id=11304}], equipment=[], step=Rinse the black-eyed peas and soak in several inches of water for 6 hours or overnight.}, {number=2, equipment=[{image=https://spoonacular.com/cdn/equipment_100x100/sauce-pan.jpg, localizedName=sauce pan, name=sauce pan, id=404669}], ingredients=[{image=https://spoonacular.com/cdn/ingredients_100x100/kidney-beans.jpg, localizedName=beans, name=beans, id=0}, {image=https://spoonacular.com/cdn/ingredients_100x100/water.png, localizedName=water, name=water, id=14412}], step=Drain and rinse, then transfer to a large saucepan and cover with fresh water. Bring to a boil, reduce heat to medium-low, cover, and simmer for 40 to 60 minutes. Take care not to overcook  the beans should be tender but not be falling apart.}, {number=3, equipment=[], ingredients=[{image=https://spoonacular.com/cdn/ingredients_100x100/eggplant.png, localizedName=eggplant, name=eggplant, id=11209}], step=Drain and set aside.To prepare the eggplant, cut of the stem and bottom edge and then cut in half lengthwise. Score the flesh into diagonal 1-inch lines, then turn and score again until you have a diagonal pattern. Take care not to cut through the skin.}, {number=4, equipment=[], ingredients=[{image=https://spoonacular.com/cdn/ingredients_100x100/water.png, localizedName=water, name=water, id=14412}, {image=https://spoonacular.com/cdn/ingredients_100x100/salt.jpg, localizedName=salt, name=salt, id=2047}], step=Sprinkle with some salt and let sit for 40 minutes. Rinse and squeeze out any excess water.}, {number=5, equipment=[{image=https://spoonacular.com/cdn/equipment_100x100/roasting-pan.jpg, localizedName=roasting pan, name=roasting pan, id=404629}], ingredients=[{image=https://spoonacular.com/cdn/ingredients_100x100/eggplant.png, localizedName=eggplant, name=eggplant, id=11209}, {image=https://spoonacular.com/cdn/ingredients_100x100/vegetable-oil.jpg, localizedName=cooking oil, name=cooking oil, id=4582}], step=Brush the eggplant with some oil and transfer to a roasting pan.}, {number=6, ingredients=[], equipment=[{image=https://spoonacular.com/cdn/equipment_100x100/oven.jpg, localizedName=oven, name=oven, id=404784}], step=Bake in a preheated 400 oven until the flesh appears collapsed and is wrinkly.}, {number=7, ingredients=[{image=https://spoonacular.com/cdn/ingredients_100x100/eggplant.png, localizedName=eggplant, name=eggplant, id=11209}, {image=https://spoonacular.com/cdn/ingredients_100x100/water.png, localizedName=water, name=water, id=14412}, {image=https://spoonacular.com/cdn/ingredients_100x100/salt.jpg, localizedName=salt, name=salt, id=2047}], equipment=[{image=https://spoonacular.com/cdn/equipment_100x100/strainer.png, localizedName=sieve, name=sieve, id=405600}], step=Remove from heat and let cool for about 10 minutes, season with a bit of salt, and remove the flesh from the eggplant. If there is too much water, drain in a strainer. Set aside.}, {number=8, equipment=[{image=https://spoonacular.com/cdn/equipment_100x100/sauce-pan.jpg, localizedName=sauce pan, name=sauce pan, id=404669}], ingredients=[{image=https://spoonacular.com/cdn/ingredients_100x100/shallots.jpg, localizedName=shallot, name=shallot, id=11677}, {image=https://spoonacular.com/cdn/ingredients_100x100/red-chili.jpg, localizedName=chili pepper, name=chili pepper, id=11819}, {image=https://spoonacular.com/cdn/ingredients_100x100/spices.png, localizedName=spices, name=spices, id=2035}, {image=https://spoonacular.com/cdn/ingredients_100x100/peas.jpg, localizedName=peas, name=peas, id=11304}, {image=https://spoonacular.com/cdn/ingredients_100x100/vegetable-oil.jpg, localizedName=cooking oil, name=cooking oil, id=4582}], step=Heat the oil over medium heat in the same saucepan used to cook the


    }
}