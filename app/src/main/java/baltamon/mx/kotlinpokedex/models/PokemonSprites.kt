package baltamon.mx.kotlinpokedex.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Baltazar Rodriguez on 10/06/2017.
 */
class PokemonSprites (val front_default: String, val back_default: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<PokemonSprites> = object : Parcelable.Creator<PokemonSprites> {
            override fun createFromParcel(source: Parcel): PokemonSprites = PokemonSprites(source)
            override fun newArray(size: Int): Array<PokemonSprites?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(front_default)
        dest.writeString(back_default)
    }
}