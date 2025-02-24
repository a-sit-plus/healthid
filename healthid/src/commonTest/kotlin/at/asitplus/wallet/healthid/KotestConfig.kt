package at.asitplus.wallet.healthid

import io.kotest.core.config.AbstractProjectConfig

class KotestConfig : AbstractProjectConfig() {
    init {
        Initializer.initWithVCK()
    }
}
