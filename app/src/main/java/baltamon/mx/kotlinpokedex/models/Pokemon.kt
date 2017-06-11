package baltamon.mx.kotlinpokedex.models

/**
 * Created by Baltazar Rodriguez on 10/06/2017.
 */
class Pokemon (val id: Int, val name: String, val height: Int, val weight: Int, val sprites: PokemonSprites,
               val moves: List<PokemonMove>, val abilities: List<PokemonAbility>,
               val types: List<PokemonType>){
}