package ahayes5

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class CharacterSheetConfiguration : Configuration() {
    private lateinit var template:String   
}