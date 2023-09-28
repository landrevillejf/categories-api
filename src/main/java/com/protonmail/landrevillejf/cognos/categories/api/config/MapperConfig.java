package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.",email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville",enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2020-01-01",
        revision = 1,
        comments = "Author DatabaseInitializer"
)
@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }
}
