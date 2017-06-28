package baltamon.mx.kotlinpokedex.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Baltazar Rodriguez on 10/06/2017.
 */
class Pokemon (val id: Int,
               val name: String,
               val height: Int,
               val weight: Int,
               val sprites: PokemonSprites,
               val moves: ArrayList<PokemonMove>,
               val abilities: ArrayList<PokemonAbility>,
               val types: ArrayList<PokemonType>) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Pokemon> = object : Parcelable.Creator<Pokemon> {
            override fun createFromParcel(source: Parcel): Pokemon = Pokemon(source)
            override fun newArray(size: Int): Array<Pokemon?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readString(),
    source.readInt(),
    source.readInt(),
    source.readParcelable<PokemonSprites>(PokemonSprites::class.java.classLoader),
    source.createTypedArrayList(PokemonMove.CREATOR),
    source.createTypedArrayList(PokemonAbility.CREATOR),
    source.createTypedArrayList(PokemonType.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeInt(height)
        dest.writeInt(weight)
        dest.writeParcelable(sprites, 0)
        dest.writeTypedList(moves)
        dest.writeTypedList(abilities)
        dest.writeTypedList(types)
    }
}