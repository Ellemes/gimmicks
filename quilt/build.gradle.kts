import org.gradle.configurationcache.extensions.capitalized

plugins {
    id("ellemes.gradle.mod").apply(false)
    id("io.github.juuxel.loom-quiltflower").version("1.7.2")
}

//val excludeFabric: (ModuleDependency) -> Unit = {
//    it.exclude("net.fabricmc")
//    it.exclude("net.fabricmc.fabric-api")
//}

dependencies {
    val qsl = mod.qsl()
    val qfapi = mod.fabricApi()

    // todo: temporary, depend on specific qsl modules
    modImplementation(qsl.module("core", "qsl_base"))
    modImplementation(qsl.module("core", "resource_loader"))
    modCompileOnly(qsl.full())
    modLocalRuntime(qsl.full())

    // todo: temporary, depend on specific qfapi modules
    modCompileOnly(qfapi.full())
    modLocalRuntime(qfapi.full())
}

val releaseModTask = tasks.getByName("releaseMod")
val modVersion = properties["mod_version"] as String
val modReleaseType = if ("alpha" in modVersion) "alpha" else if ("beta" in modVersion) "beta" else "release"
var modChangelog = rootDir.resolve("changelog.md").readText(Charsets.UTF_8)
val modTargetVersions = mutableListOf(properties["minecraft_version"] as String)
val modUploadDebug = System.getProperty("MOD_UPLOAD_DEBUG", "false") == "true" // -DMOD_UPLOAD_DEBUG=true

fun String.execute() = org.codehaus.groovy.runtime.ProcessGroovyMethods.execute(this)
val Process.text: String? get() = org.codehaus.groovy.runtime.ProcessGroovyMethods.getText(this)
val commit = "git rev-parse HEAD".execute().text
modChangelog += "\nCommit: https://github.com/Ellemes/gimmicks/commit/$commit"

(properties["extra_game_versions"] as String).split(",").forEach {
    if (it != "") {
        modTargetVersions.add(it)
    }
}

curseforge {
    options(closureOf<me.hypherionmc.cursegradle.Options> {
        debug = modUploadDebug
        javaVersionAutoDetect = false
        javaIntegration = false
        forgeGradleIntegration = false
        fabricIntegration = false
        detectFabricApi = false
    })

    project(closureOf<me.hypherionmc.cursegradle.CurseProject> {
        apiKey = System.getenv("CURSEFORGE_TOKEN")
        id = properties["curseforge_project_id"]
        releaseType = modReleaseType
        mainArtifact(tasks.getByName("minJar"), closureOf<me.hypherionmc.cursegradle.CurseArtifact> {
            displayName = project.name.capitalized() + " " + modVersion
            artifact = tasks.getByName("minJar")
        })
        relations(closureOf<me.hypherionmc.cursegradle.CurseRelation> {
            //requiredDependency("qsl")
        })
        changelogType = "markdown"
        changelog = modChangelog
        gameVersionStrings = listOf(project.name.capitalized(), "Java " + java.targetCompatibility.majorVersion) + modTargetVersions
    })
}

modrinth {
    debugMode.set(modUploadDebug)
    detectLoaders.set(false)

    projectId.set(properties["modrinth_project_id"] as String)
    versionType.set(modReleaseType)
    versionNumber.set(modVersion  + "+" + project.name)
    versionName.set(project.name.capitalized() + " " + modVersion)
    uploadFile.set(tasks.getByName("minJar"))
    dependencies {
        required.project("qvIfYCYJ") // qsl
    }
    changelog.set(modChangelog)
    gameVersions.set(modTargetVersions)
    loaders.set(listOf(project.name))
}

afterEvaluate {
    releaseModTask.finalizedBy(listOf("modrinth", "curseforge" + properties["curseforge_project_id"]))
}
