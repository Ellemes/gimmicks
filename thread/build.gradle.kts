plugins {
    id("ellemes.gradle.mod").apply(false)
}

dependencies {
    val fapi = mod.fabricApi()

    // todo: temporary, depend on specific qfapi modules
    modCompileOnly(fapi.full())
    modLocalRuntime(fapi.full())
}
