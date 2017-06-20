package baltamon.mx.kotlinpokedex.data

import baltamon.mx.kotlinpokedex.models.NamedAPIResource

/**
 * Created by Baltazar Rodriguez on 10/06/2017.
 */
data class Ability (val id: Int,
                    val name: String,
                    val effect_entries: List<VerboseEffect>)

data class APIResource(val url: String)

data class APIResourceList(val count: Int,
                           val next: String,
                           val previous: Boolean,
                           val results: List<APIResource>)

data class Move (val id: Int,
                 val name: String,
                 val pp: Int,
                 val power: Int,
                 val damage_class: NamedAPIResource)

data class NamedAPIResourceList(val count: Int,
                                val next: String,
                                val previous: Boolean,
                                val results: List<NamedAPIResource>)

data class Type(val id: Int, val name: String)

data class VerboseEffect(val effect: String, val short_effect: String)