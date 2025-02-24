package at.asitplus.wallet.healthid

import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation.SD_JWT
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation.ISO_MDOC
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialScheme

object HealthIdScheme : CredentialScheme {

    override val schemaUri: String = "https://wallet.a-sit.at/schemas/1.0.0/healthid.json"
    override val sdJwtType: String = "urn:eu.europa.ec.eudi:hiid:1"
    override val isoDocType: String = "eu.europa.ec.eudi.hiid.1"
    override val isoNamespace: String = "eu.europa.ec.eudi.hiid.1"
    override val supportedRepresentations: Collection<CredentialRepresentation> =
        listOf(SD_JWT, ISO_MDOC)

    override val claimNames: Collection<String> = listOf(
        Attributes.HEALTH_INSURANCE_ID,
        Attributes.PATIENT_ID,
        Attributes.TAX_NUMBER,
        Attributes.ONE_TIME_TOKEN,
        Attributes.E_PRESCRIPTION_CODE,
        Attributes.AFFILIATION_COUNTRY,
        Attributes.ISSUE_DATE,
        Attributes.EXPIRY_DATE,
        Attributes.ISSUING_AUTHORITY,
        Attributes.DOCUMENT_NUMBER,
        Attributes.ADMINISTRATIVE_NUMBER,
        Attributes.ISSUING_COUNTRY,
        Attributes.ISSUING_JURISDICTION,
    )


    object Attributes {
        /**
         * Character string that enables the health care provider to identify the person via international search mask
         * (national format)
         */
        const val HEALTH_INSURANCE_ID = "health_insurance_id"

        /**
         * Character string that enables the health care provider to identify the person via international search mask
         * (national format)
         */
        const val PATIENT_ID = "patient_id"

        /**
         * Character string that enables the health care provider to identify the person via international search mask
         * (national format)
         */
        const val TAX_NUMBER = "tax_number"

        /**
         * Character string that enables the health care provider to identify the person via international search mask
         * (national format)
         */
        const val ONE_TIME_TOKEN = "one_time_token"

        /**
         * Character string that enables the health care provider to identify the person via international search mask
         * (national format). Contains TaskID, Access Code and human readable part separated with a `|` (pipe char)
         */
        const val E_PRESCRIPTION_CODE = "wallet_e_prescription_code"

        /**
         * Alpha-2 country code, as defined in ISO 3166-1, of the user’s country of tax residence of affiliation as
         * used in the international search mask
         */
        const val AFFILIATION_COUNTRY = "affiliation_country"

        /** Date and time when the Health ID attestation was issued. */
        const val ISSUE_DATE = "issue_date"

        /** Date and time when the Health ID attestation will expire. */
        const val EXPIRY_DATE = "expiry_date"

        /**
         * Name of the administrative authority that has issued this Health ID attestation, or
         * the ISO 3166 Alpha-2 country code of the respective Member State if
         * there is no separate authority authorized to issue health insurance attestations.
         */
        const val ISSUING_AUTHORITY = "issuing_authority"

        /** A number for the Health ID attestation, assigned by the Provider. */
        const val DOCUMENT_NUMBER = "document_number"

        /** A number assigned by the Health ID attestation Provider for audit control or other purposes. */
        const val ADMINISTRATIVE_NUMBER = "administrative_number"

        /** Alpha-2 country code, as defined in ISO 3166-1, of the Health ID attestation Provider's country or territory. */
        const val ISSUING_COUNTRY = "issuing_country"

        /**
         * Country subdivision code of the jurisdiction that issued the attestation, as
         * defined in ISO 3166-2:2020, Clause 8. The first part of the code SHALL
         * be the same as the value for [ISSUING_COUNTRY].
         */
        const val ISSUING_JURISDICTION = "issuing_jurisdiction"

    }
}
