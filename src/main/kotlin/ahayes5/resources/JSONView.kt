package ahayes5.resources
import com.fasterxml.jackson.databind.ObjectMapper

object JSONView {
    private val mapper = ObjectMapper()

    operator fun invoke(c: Any):String {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(c)
    }
}