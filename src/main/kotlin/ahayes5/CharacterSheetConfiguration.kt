package ahayes5

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory

class CharacterSheetConfiguration : Configuration() {
    val database = DataSourceFactory()
}