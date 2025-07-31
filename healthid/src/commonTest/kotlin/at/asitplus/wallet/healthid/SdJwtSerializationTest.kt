package at.asitplus.wallet.healthid

import at.asitplus.signum.indispensable.cosef.io.coseCompliantSerializer
import at.asitplus.wallet.lib.data.vckJsonSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.provided.randomInstant
import io.kotest.provided.randomString
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray

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

        val cbor = coseCompliantSerializer.encodeToByteArray(credential)
        coseCompliantSerializer.decodeFromByteArray<HealthID>(cbor) shouldBe credential
    }

})
