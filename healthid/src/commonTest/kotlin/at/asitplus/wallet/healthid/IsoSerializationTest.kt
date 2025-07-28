package at.asitplus.wallet.healthid

import at.asitplus.iso.IssuerSignedItemSerializer
import at.asitplus.signum.indispensable.cosef.*
import at.asitplus.signum.indispensable.cosef.io.coseCompliantSerializer
import at.asitplus.signum.supreme.sign.EphemeralKey
import at.asitplus.signum.supreme.signature
import at.asitplus.wallet.healthid.HealthIdScheme.Attributes
import at.asitplus.wallet.lib.agent.SubjectCredentialStore
import at.asitplus.wallet.lib.data.CredentialToJsonConverter
import at.asitplus.wallet.lib.iso.*
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.provided.randomInstant
import io.kotest.provided.randomString
import kotlinx.serialization.json.JsonObject
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.time.Clock

class IsoSerializationTest : FreeSpec({

    "Serialization and deserialization" - {
        withData(nameFn = { "for ${it.key}" }, dataMap().entries) {
            val item = it.toIssuerSignedItem()

            val serialized = coseCompliantSerializer.encodeToByteArray(
                IssuerSignedItemSerializer(
                    HealthIdScheme.isoNamespace,
                    it.key
                ), item
            )


            val deserialized = coseCompliantSerializer.decodeFromByteArray(
                IssuerSignedItemSerializer(
                    HealthIdScheme.isoNamespace,
                    it.key
                ), serialized
            ) shouldBe item

            deserialized.elementValue shouldBe it.value
        }
    }

    "Serialization to JSON Element" {
        val mso = MobileSecurityObject(
            version = "1.0",
            digestAlgorithm = "SHA-256",
            valueDigests = mapOf("foo" to ValueDigestList(listOf(ValueDigest(0U, byteArrayOf())))),
            deviceKeyInfo = deviceKeyInfo(),
            docType = "docType",
            validityInfo = ValidityInfo(Clock.System.now(), Clock.System.now(), Clock.System.now())
        )
        val rsaSig = EphemeralKey {
            rsa
        }.getOrThrow().signer { }.getOrThrow().sign(byteArrayOf(1, 3, 3, 7)).signature
        val claims = dataMap()
        val namespacedItems: Map<String, List<IssuerSignedItem>> =
            mapOf(HealthIdScheme.isoNamespace to claims.map { it.toIssuerSignedItem() }.toList())
        val issuerAuth = CoseSigned.create(
            CoseHeader(), null, mso, rsaSig,
            MobileSecurityObject.serializer()
        )
        val credential = SubjectCredentialStore.StoreEntry.Iso(
            IssuerSigned.fromIssuerSignedItems(namespacedItems, issuerAuth),
            HealthIdScheme.isoNamespace
        )
        val converted = CredentialToJsonConverter.toJsonElement(credential)
            .shouldBeInstanceOf<JsonObject>()
            .also { println(it) }
        val jsonMap = converted[HealthIdScheme.isoNamespace]
            .shouldBeInstanceOf<JsonObject>()

        claims.forEach {
            withClue("Serialization for ${it.key}") {
                jsonMap[it.key].shouldNotBeNull()
            }
        }
    }
})

private fun Map.Entry<String, Any>.toIssuerSignedItem() =
    IssuerSignedItem(Random.nextUInt(), Random.nextBytes(32), key, value)

private fun dataMap(): Map<String, Any> = mapOf(
    Attributes.HEALTH_INSURANCE_ID to randomString(),
    Attributes.PATIENT_ID to randomString(),
    Attributes.TAX_NUMBER to randomString(),
    Attributes.ONE_TIME_TOKEN to randomString(),
    Attributes.E_PRESCRIPTION_CODE to randomString(),
    Attributes.AFFILIATION_COUNTRY to randomString(),
    Attributes.ISSUE_DATE to randomInstant(),
    Attributes.EXPIRY_DATE to randomInstant(),
    Attributes.ISSUING_AUTHORITY to randomString(),
    Attributes.DOCUMENT_NUMBER to randomString(),
    Attributes.ADMINISTRATIVE_NUMBER to randomString(),
    Attributes.ISSUING_COUNTRY to randomString(),
    Attributes.ISSUING_JURISDICTION to randomString(),
)

private fun deviceKeyInfo() =
    DeviceKeyInfo(CoseKey(CoseKeyType.EC2, keyParams = CoseKeyParams.EcYBoolParams(CoseEllipticCurve.P256)))
