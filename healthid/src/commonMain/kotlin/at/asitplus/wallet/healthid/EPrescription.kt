package at.asitplus.wallet.healthid


import at.asitplus.wallet.healthid.EPrescriptionDataElements.COUNTRY_CODE
import at.asitplus.wallet.healthid.EPrescriptionDataElements.OTT
import at.asitplus.wallet.healthid.EPrescriptionDataElements.VALID_UNTIL
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EPrescription(

    @SerialName(OTT)
    val oneTimeToken: String,

    @SerialName(COUNTRY_CODE)
    val countryCode: String,

    @SerialName(VALID_UNTIL)
    val validUntil: Instant,
)

