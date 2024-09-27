package at.asitplus.wallet.eprescription

import at.asitplus.wallet.lib.data.vckJsonSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import kotlin.random.Random

class SerializerTest : FunSpec({

    test("serialize credential") {
        val credential = EPrescription(
            oneTimeToken = randomString(),
            countryCode = randomString(),
            validUntil = randomInstant(),
        )
        val serialized = vckJsonSerializer.encodeToString(credential)
            .also { println(it) }

        val parsed: EPrescription = vckJsonSerializer.decodeFromString(serialized)

        parsed shouldBe credential
    }

})

private fun randomInstant() = Instant.fromEpochSeconds(Random.nextLong(0, 1024 * 1024 * 1024))

val charPool = ('A'..'Z') + ('a'..'z') + ('0'..'9')

fun randomString() = (1..16)
    .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
    .joinToString("")
