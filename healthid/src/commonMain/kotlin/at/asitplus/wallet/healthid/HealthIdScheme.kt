package at.asitplus.wallet.healthid

import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation.SD_JWT
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialScheme

object HealthIdScheme : CredentialScheme {

    override val schemaUri: String = "https://wallet.a-sit.at/schemas/1.0.0/eprescription.json"
    override val sdJwtType: String = "eu.europa.ehealth.eprescription.1"
    override val supportedRepresentations: Collection<CredentialRepresentation> =
        listOf(SD_JWT)
}
