package at.asitplus.wallet.eprescription

import at.asitplus.wallet.lib.LibraryInitializer

object Initializer {

    /**
     * A reference to this class is enough to trigger the init block
     */
    init {
        initWithVcLib()
    }

    /**
     * This has to be called first, before anything first, to load the
     * relevant classes of this library into the base implementations of vclib
     */
    fun initWithVcLib() {
        LibraryInitializer.registerExtensionLibrary(
            credentialScheme = EPrescriptionScheme,
        )
    }

}
