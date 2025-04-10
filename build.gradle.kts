import com.android.build.gradle.internal.packaging.defaultMerges
import kotlinx.coroutines.flow.merge

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

dependencies {
    kover(project(":app"))
    kover(project(":util-logging"))
    kover(project(":util"))
    kover(project(":ui-core"))
    kover(project(":data:model"))
    kover(project(":data:api:config"))
    kover(project(":data:api:model"))
    kover(project(":data:api:impl-ktor"))
    kover(project(":data:api:impl-retrofit"))
    kover(project(":data:db:room"))
    kover(project(":data:source:local:impl-room"))
    kover(project(":data:source:remote:impl-ktor"))
    kover(project(":data:source:remote:impl-retrofit"))
    kover(project(":data:db:object-box"))
    kover(project(":data:db:realm"))
    kover(project(":data:source:local:impl-objectBox"))
    kover(project(":data:source:local:impl-realm"))
    kover(project(":store:action"))
    kover(project(":store:feature"))
    kover(project(":store:state"))
    kover(project(":store:env"))
    kover(project(":store:base"))
    kover(project(":store:app-store"))
    kover(project(":feature:home:reducer"))
    kover(project(":feature:movie:details:reducer"))
    kover(project(":feature:home:ui"))
    kover(project(":feature:movie:details:ui"))
    kover(project(":feature:common"))
}

val codeCoverageExcludeList = listOf(
    // Project specific UI exclusions
    "**/ui/components/*",
    "**/*UiTags.*",
    "**/*Screen*.*",
    "**/*ScreenUi*.*",
    "**/*ScaffoldContent*.*",
    "**/*UiPreview*.*",
    "**/*Ui*Preview*.*",
    "**/*Event.*",
    "**/*Module.*",
    "**/*PreviewData*.*", //`app`, ui-core module(s) classes
    "**/*UiData*.*", //`app`, ui-core module(s) Ui Data Model classes
    "**/*DataModel*.*", //`data` module DataModel classes
//        "**/*ApiModel.*", //`data/api/model` module ApiModel classes

    // Project specific common exclusions
    "**/RootUtils.kt",
    "**/*WorkerService.*",
    "**/di/*",

    // Common exclusions
    "**/BuildConfig.*",
    "**/*Test*.*",
    "**/R.class",
    "**/R$*.class",
    "**/*$*",
    "**/Manifest*.*",
    "android/**/*.*",
    "**/*Dagger*.*",
    "**/Dagger*Component*.*",
    "**/*_Factory*.*",
    "**/*_Provide*Factory*.*",
    "**/*Companion*.*",
    "**/*MembersInjector*.*",
    "**/*_MembersInjector.class",
    "**/*Component*.*",
    "**/*Extensions*.*",
    "**/Lambda$*.class",
    "**/*\$Lambda$*.*",
    "**/Lambda.class",
    "**/*Lambda.class",
    "**/*Lambda*.class",
    "**/*Module_*Factory.class",

    // Kotlin specific exclusions
    "**/*\$Properties*", // Kotlin properties
    "**/*\$Companion*",
    "**/BuildConfig*",
    "**/*\$Initializer*",
    "**/*\$Creator*" // Parcelable creators
)

kover {
    reports {
        total {
            xml {
                onCheck = false
            }
            html {
                onCheck = true
            }
        }
        filters {
            excludes {
                androidGeneratedClasses()
                classes(codeCoverageExcludeList)
            }
        }
    }
}

buildscript {
    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
        google()
    }

    dependencies {
        classpath(libs.ktlint.plugin)
        classpath(libs.detekt)
        classpath(libs.objectBox)
        classpath(libs.realm.plugin)

    }
}

allprojects {
    /**
     * ./gradlew detekt
     * ./gradlew detektBaseline - prefer using this one
     */
    apply {
        plugin(rootProject.libs.plugins.detekt.get().pluginId)
    }
}

/**
 * Lint terminal https://developer.android.com/studio/write/lint
 * ./gradlew lint
 * ./gradlew lintDebug
 * ./gradlew lintRelease
 */


/**
 * Run all tests
 * https://developer.android.com/studio/test/command-line
 *
 * ./gradlew test
 *
 * ./gradlew connectedAndroidTest
 * ./gradlew cAT
 */

/**
 * Mac
 * chmod +x gradlew (id needed, https://stackoverflow.com/questions/17668265/gradlew-permission-denied)
 * ./gradlew ktlintCheck
 * ./gradlew ktlintFormat
 *
 * Windows
 * gradlew ktlintCheck
 * gradlew ktlintFormat
 */

subprojects {
    apply {
        plugin(rootProject.libs.plugins.ktlint.get().pluginId) // Version should be inherited from parent
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        verbose.set(true)
        android.set(true)

        outputToConsole.set(true)
        outputColorName.set("RED")

        relative.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(false) // https://github.com/pinterest/ktlint/blob/master/ktlint-ruleset-experimental/src/main/kotlin/com/pinterest/ktlint/ruleset/experimental/ExperimentalRuleSetProvider.kt
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

// https://detekt.dev/docs/gettingstarted/gradle#kotlin-dsl-3
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    config.setFrom(file("config/detekt/detekt.yml"))
    baseline.set(file("${rootProject.projectDir}/config/detekt/baseline.xml"))
    include("**/*.kt")
    exclude("**/build/**")
    setSource(projectDir)
    allRules = false
    parallel = false
    disableDefaultRuleSets = false
    buildUponDefaultConfig = true
    debug = false
    ignoreFailures = false
    basePath = projectDir.absolutePath
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
    jvmTarget = GradleConfig.javaVersionAsString
}

/**
 * https://detekt.dev/docs/introduction/baseline/
 */
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = GradleConfig.javaVersionAsString
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("config/detekt/detekt.yml"))
    baseline.set(file("config/detekt/baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

tasks.register<JacocoReport>("jacocoReport") {
    reports {
        csv.required.set(false)
        xml.required.set(false)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco"))
    }

    fun Project.isJavaModule(): Boolean = plugins.hasPlugin("java")
    fun Project.isAndroidLibrary(): Boolean = plugins.hasPlugin("com.android.library")
    fun Project.isAndroidApp(): Boolean = plugins.hasPlugin("com.android.application")

    val javaModules = subprojects.filter { it.isJavaModule() }
    val androidModules = subprojects.filter { it.isAndroidLibrary() || it.isAndroidApp() }

    dependsOn(javaModules.map { it.tasks.named("test") })
    dependsOn(androidModules.map { it.tasks.named("testDebugUnitTest") })

    sourceDirectories.setFrom(
        files(
            javaModules.map { it.fileTree("src/main/java") },
            javaModules.map { it.fileTree("src/main/kotlin") },
            androidModules.map { it.fileTree("src/main/java") },
            androidModules.map { it.fileTree("src/main/kotlin") }
        )
    )

    classDirectories.setFrom(
        files(
            javaModules.map { module ->
                module.fileTree("build/classes/java/main") {
                    exclude(codeCoverageExcludeList)
                }
                module.fileTree("build/classes/kotlin/main") {
                    exclude(codeCoverageExcludeList)
                }
            },
            androidModules.map { module ->
                module.fileTree("${module.layout.buildDirectory.get()}/tmp/kotlin-classes/debug") {
                    exclude(codeCoverageExcludeList)
                }
                module.fileTree("${module.layout.buildDirectory.get()}/tmp/kotlin-classes/release") {
                    exclude(codeCoverageExcludeList)
                }
            }
        )
    )

    executionData.setFrom(
        files(
            javaModules.map { "${it.layout.buildDirectory.get()}/jacoco/test.exec" },
            androidModules.map {
                // 'app' module
                "${it.layout.buildDirectory.get()}/jacoco/testDebugUnitTest.exec"
//                "${it.layout.buildDirectory.get()}/jacoco/testReleaseUnitTest.exec"

                // 'ui-core' module
                "${it.layout.buildDirectory.get()}/jacoco/testDebugUnitTest.exec"
//                "${it.layout.buildDirectory.get()}/jacoco/testReleaseUnitTest.exec"
            }
        )
    )
}
