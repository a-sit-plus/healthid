package at.asitplus.wallet.healthid

import at.asitplus.wallet.lib.data.vckJsonSerializer
import at.asitplus.wallet.lib.iso.vckCborSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.encodeToString

@OptIn(ExperimentalSerializationApi::class)
class SdJwtSerializationTest : FunSpec({

    test("serialize credential") {
        val credential = HealthID(
            healthInsuranceId = randomString(),
            patientId = randomString(),
            taxNumber = randomString(),
            oneTimeToken = randomString(),
            ePrescriptionCode = randomString(),
            affiliationCountry = randomString(),
            issueDate = randomInstant(),
            expiryDate = randomInstant(),
            issuingAuthority = randomString(),
            documentNumber = randomString(),
            administrativeNumber = randomString(),
            issuingCountry = randomString(),
            issuingJurisdiction = randomString(),
        )
        val json = vckJsonSerializer.encodeToString(credential)
        vckJsonSerializer.decodeFromString<HealthID>(json) shouldBe credential

        val cbor = vckCborSerializer.encodeToByteArray(credential)
        vckCborSerializer.decodeFromByteArray<HealthID>(cbor) shouldBe credential
    }

})
