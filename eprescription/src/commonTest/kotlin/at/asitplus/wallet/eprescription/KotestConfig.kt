package at.asitplus.wallet.eprescription

import io.kotest.core.config.AbstractProjectConfig

class KotestConfig : AbstractProjectConfig() {
    init {
        Initializer.initWithVcLib()
    }
}
