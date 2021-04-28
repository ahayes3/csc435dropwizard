package ahayes5

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import javax.validation.Valid
import javax.validation.constraints.NotNull

class CharacterSheetConfiguration : Configuration() {

    @Valid
    @NotNull
    var database = DataSourceFactory()

    @JsonProperty("database")
    fun getDataSourceFactory():DataSourceFactory {
        return database
    }

    @JsonProperty("database")
    fun setDataSourceFactory(dataSourceFactory: DataSourceFactory) {
        this.database = dataSourceFactory
    }
}