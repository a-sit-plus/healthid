package at.asitplus.wallet.healthid


object EPrescriptionDataElements {
    const val OTT = "one_time_token"
    const val COUNTRY_CODE = "country_code"
    const val VALID_UNTIL = "valid_until"

    val MANDATORY_ELEMENTS = listOf(
        OTT,
        COUNTRY_CODE,
        VALID_UNTIL,
    )

    val ALL_ELEMENTS = listOf(
        OTT,
        COUNTRY_CODE,
        VALID_UNTIL,
    )

}
