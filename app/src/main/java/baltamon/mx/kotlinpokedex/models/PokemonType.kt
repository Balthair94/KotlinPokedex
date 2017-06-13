package baltamon.mx.kotlinpokedex.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Baltazar Rodriguez on 10/06/2017.
 */
class PokemonType (val type: NamedAPIResource) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PokemonType> = object : Parcelable.Creator<PokemonType> {
            override fun createFromParcel(source: Parcel): PokemonType = PokemonType(source)
            override fun newArray(size: Int): Array<PokemonType?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readParcelable<NamedAPIResource>(NamedAPIResource::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(type, 0)
    }
}