import Wartremover.CompilerConfiguration
import com.github.uharaqo.hocon.mapper.load
import com.typesafe.config.ConfigFactory
import kotlinx.serialization.Serializable
import java.io.File

/** Extension for wartremover for scala. */
object Wartremover {
    /**
     * @param file the specified file
     * @return the configuration of WartRemover extracted from the specified file
     */
    fun configFile(file: File): WartRemoverCompilerConfiguration =
        WartRemoverCompilerConfiguration.serializer().load(ConfigFactory.parseFile(file))

    /** Configuration for the parameters of the scala compiler. */
    @FunctionalInterface
    fun interface CompilerConfiguration {
        /** @return a list of parameters for the configuration of the scala compiler */
        fun toCompilerOptions(): List<String>

        /**
         * @param other the specified configuration
         * @return a new configuration that combines this configuration with the specified configuration
         */
        operator fun plus(other: CompilerConfiguration): CompilerConfiguration =
            CompilerConfiguration { this.toCompilerOptions() + other.toCompilerOptions() }
    }

    /** Configuration for the parameters of the WartRemover plugin for the scala compiler. */
    @Serializable
    data class WartRemoverCompilerConfiguration(
        private val warts: Map<String, TraverserType>
    ) : CompilerConfiguration {
        override fun toCompilerOptions(): List<String> =
            this.warts
                .filter { it.value != TraverserType.Ignore }
                .map {
                    when (it.value) {
                        TraverserType.Error -> errorTraverser(it.key)
                        TraverserType.Warning -> warningTraverser(it.key)
                        else -> throw NotImplementedError(
                            "The traverser type for {$it} has not been implemented yet."
                        )
                    }
                }

        companion object {
            private fun errorTraverser(wart: String): String = traverser("traverser", wart)
            private fun warningTraverser(wart: String): String = traverser("only-warn-traverser", wart)
            private fun traverser(type: String, wart: String): String =
                "-P:wartremover:$type:org.wartremover.warts.$wart"
        }
    }

    /** AST traverser type for WartRemover. */
    enum class TraverserType {
        /** Treat warts as errors. */ Error,
        /** Treat warts as warnings. */ Warning,
        /** Ignore warts. */ Ignore
    }
}
