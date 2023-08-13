package com.baeldung.jsonschemageneration.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JacksonModuleSchemaGenerator {
    public static void main(String[] args) {

        JacksonModule module = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON).with(module).with(Option.EXTRA_OPEN_API_FORMAT_VALUES);

        SchemaGenerator generator = new SchemaGenerator(configBuilder.build());
        JsonNode jsonSchema = generator.generateSchema(Person.class);

        System.out.println(jsonSchema.toPrettyString());
    }

    static class Person {

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id;

        @JsonProperty(access = JsonProperty.Access.READ_WRITE, required = true)
        String name;

        @JsonProperty(access = JsonProperty.Access.READ_WRITE, required = true)
        String surname;

        @JsonProperty(access = JsonProperty.Access.READ_WRITE, required = true)
        Address address;

        @JsonIgnore
        String fullName;

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Date createdAt;

        @JsonProperty(access = JsonProperty.Access.READ_WRITE)
        List<Person> friends;

    }

    static class Address {

        @JsonProperty()
        String street;

        @JsonProperty(required = true)
        String city;

        @JsonProperty(required = true)
        String country;
    }

}
