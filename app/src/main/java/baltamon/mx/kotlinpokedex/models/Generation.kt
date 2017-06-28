package baltamon.mx.kotlinpokedex.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Baltazar Rodriguez on 17/06/2017.
 */
class Generation (val id: Int,
                  val name: String,
                  val abilities: ArrayList<NamedAPIResource>,
                  val moves: ArrayList<NamedAPIResource>,
                  val pokemon_species: ArrayList<NamedAPIResource>,
                  val types: ArrayList<NamedAPIResource>) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Generation> = object : Parcelable.Creator<Generation> {
            override fun createFromParcel(source: Parcel): Generation = Generation(source)
            override fun newArray(size: Int): Array<Generation?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readString(),
    source.createTypedArrayList(NamedAPIResource.CREATOR),
    source.createTypedArrayList(NamedAPIResource.CREATOR),
    source.createTypedArrayList(NamedAPIResource.CREATOR),
    source.createTypedArrayList(NamedAPIResource.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeTypedList(abilities)
        dest.writeTypedList(moves)
        dest.writeTypedList(pokemon_species)
        dest.writeTypedList(types)
    }
}