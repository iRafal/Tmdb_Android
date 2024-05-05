/**
 * inspired by: https://gist.github.com/alexpopa95/43b493c073fc0bd6d4f074c7809f9cd4
 * 1.2.3-RC4, 2.4.7-SNAPSHOT,  5.8.2 => major.minor.patch-suffix (suffix is not used for final release)
 */
class Version(
    val major: Int,
    val minor: Int,
    val patch: Int? = null,
    val suffix: Suffix = Suffix.None
) {

    sealed class Suffix(open val name: String) {
        object Snapshot : Suffix("SNAPSHOT")
        class RC(val number: Int) : Suffix("RC$number")
        class Custom(override val name: String) : Suffix(name)
        object None : Suffix("")
    }

    val name: String
        get() {
            return when (suffix) {
                is Suffix.RC -> {
                    if (suffix.number <= 0) {
                        throw IllegalArgumentException("Wrong suffix name: $suffix")
                    }
                    "$versionBasePart-${suffix.name}"
                }
                is Suffix.Custom -> {
                    if (suffix.name.isEmpty() || suffix.name.isBlank()) {
                        throw IllegalArgumentException("Wrong suffix name: $suffix")
                    }
                    "$versionBasePart-${suffix.name}"
                }
                Suffix.Snapshot -> "$versionBasePart-${suffix.name}"
                Suffix.None -> versionBasePart
            }
        }

    private val versionBasePart: String
        get() {
            return if (patch == null) {
                "$major.$minor"
            } else {
                if (patch < 0) throw IllegalArgumentException("Wrong patch value: $patch")

                "$major.$minor.$patch"
            }
        }
}
