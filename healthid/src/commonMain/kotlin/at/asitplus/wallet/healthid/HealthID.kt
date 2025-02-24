package at.asitplus.wallet.healthid


import at.asitplus.wallet.healthid.HealthIdScheme.Attributes
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HealthID(

    /**
     * Character string that enables the health care provider to identify the person via international search mask
     * (national format)
     */
    @SerialName(Attributes.HEALTH_INSURANCE_ID)
    val healthInsuranceId: String? = null,

    /**
     * Character string that enables the health care provider to identify the person via international search mask
     * (national format)
     */
    @SerialName(Attributes.PATIENT_ID)
    val patientId: String? = null,

    /**
     * Character string that enables the health care provider to identify the person via international search mask
     * (national format)
     */
    @SerialName(Attributes.TAX_NUMBER)
    val taxNumber: String? = null,

    /**
     * Character string that enables the health care provider to identify the person via international search mask
     * (national format)
     */
    @SerialName(Attributes.ONE_TIME_TOKEN)
    val oneTimeToken: String? = null,

    /**
     * Character string that enables the health care provider to identify the person via international search mask
     * (national format). Contains TaskID, Access Code and human readable part separated with a `|` (pipe char)
     */
    @SerialName(Attributes.E_PRESCRIPTION_CODE)
    val ePrescriptionCode: String? = null,

    /**
     * Alpha-2 country code, as defined in ISO 3166-1, of the user’s country of tax residence of affiliation as
     * used in the international search mask
     */
    @SerialName(Attributes.AFFILIATION_COUNTRY)
    val affiliationCountry: String? = null,

    /** Date and time when the Health ID attestation was issued. */
    @SerialName(Attributes.ISSUE_DATE)
    val issueDate: Instant,

    /** Date and time when the Health ID attestation will expire. */
    @SerialName(Attributes.EXPIRY_DATE)
    val expiryDate: Instant,

    /**
     * Name of the administrative authority that has issued this Health ID attestation, or
     * the ISO 3166 Alpha-2 country code of the respective Member State if
     * there is no separate authority authorized to issue health insurance attestations.
     */
    @SerialName(Attributes.ISSUING_AUTHORITY)
    val issuingAuthority: String,

    /** A number for the Health ID attestation, assigned by the Provider. */
    @SerialName(Attributes.DOCUMENT_NUMBER)
    val documentNumber: String? = null,

    /** A number assigned by the Health ID attestation Provider for audit control or other purposes. */
    @SerialName(Attributes.ADMINISTRATIVE_NUMBER)
    val administrativeNumber: String? = null,

    /** Alpha-2 country code, as defined in ISO 3166-1, of the Health ID attestation Provider's country or territory. */
    @SerialName(Attributes.ISSUING_COUNTRY)
    val issuingCountry: String,

    /**
     * Country subdivision code of the jurisdiction that issued the attestation, as
     * defined in ISO 3166-2:2020, Clause 8. The first part of the code SHALL
     * be the same as the value for [issuingCountry].
     */
    @SerialName(Attributes.ISSUING_JURISDICTION)
    val issuingJurisdiction: String? = null,

    ) {
    init {
        require(healthInsuranceId != null || patientId != null || taxNumber != null || oneTimeToken != null || ePrescriptionCode != null) { "At least one identifier needs to be set" }
    }
}