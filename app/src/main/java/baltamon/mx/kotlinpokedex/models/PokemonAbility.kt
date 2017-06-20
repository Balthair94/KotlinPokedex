package baltamon.mx.kotlinpokedex.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Baltazar Rodriguez on 10/06/2017.
 */
class PokemonAbility (val ability: NamedAPIResource) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PokemonAbility> = object : Parcelable.Creator<PokemonAbility> {
            override fun createFromParcel(source: Parcel): PokemonAbility = PokemonAbility(source)
            override fun newArray(size: Int): Array<PokemonAbility?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readParcelable<NamedAPIResource>(NamedAPIResource::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(ability, 0)
    }
}