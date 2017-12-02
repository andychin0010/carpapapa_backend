package com.carpapapa.configuration.spring;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by chandler on 7/12/17.
 */
@Configuration
public class GraphQLConfig {

//    GraphQLObjectType queryType = newObject()
//            .name("helloWorldQuery")
//            .field(newFieldDefinition()
//                    .type(GraphQLString)
//                    .name("hello")
//                    .staticValue("world"))
//            .build();

//    GraphQLSchema schema = GraphQLSchema.newSchema()
//            .query(queryType)
//            .build();

//    GraphQL graphQL = GraphQL.newGraphQL(schema).build();

    @Bean
    public GraphQLObjectType graphQLObjectType() {
        return newObject()
            .name("helloWorldQuery")
            .field(newFieldDefinition()
                    .type(GraphQLString)
                    .name("hello")
                    .staticValue("world"))
            .build();
    }

    @Bean
    public GraphQLSchema graphQLSchema(GraphQLObjectType queryType) {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

//        ClassLoader classLoader = getClass().getClassLoader();
//        File schemaFile = new File(getClass().getClassLoader().getResource("starWarsSchema.graphqls").getFile());
        ClassPathResource res = new ClassPathResource("starWarsSchema.graphqls");

        InputStream inputStream = null;
        try {
            inputStream = res.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reader targetReader = new InputStreamReader(inputStream);
//        File schemaFile = new File(input);

        TypeDefinitionRegistry typeRegistry = schemaParser.parse(targetReader);
        RuntimeWiring wiring = buildRuntimeWiring();
        return schemaGenerator.makeExecutableSchema(typeRegistry, wiring);

//        return GraphQLSchema.newSchema()
//                .query(queryType)
//                .build();
    }

    @Bean
    public GraphQL graphQL(GraphQLSchema schema) {
        return GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("QueryType", typeWiring -> typeWiring
                        .dataFetcher("hero", new StaticDataFetcher(test()))).build();

//        return RuntimeWiring.newRuntimeWiring().build();
    }

    private String test() {
        return "testingw";
    }
}
