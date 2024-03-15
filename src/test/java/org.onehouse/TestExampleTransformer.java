package org.onehouse;

import org.apache.avro.Schema;
import org.apache.commons.io.IOUtils;
import org.apache.hudi.AvroConversionUtils;
import org.apache.hudi.common.config.TypedProperties;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.glassfish.jersey.internal.inject.Custom;
import org.jets3t.service.model.cloudfront.CustomOrigin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mortbay.log.Log;
import scala.reflect.ClassTag;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class TestExampleTransformer{
    private static SparkSession spark;

    private static JavaSparkContext jsc;
    private static StructType schema;
    private static Dataset<Row> inputDF;

    @BeforeAll
    static void setUp() throws IOException {
        spark = SparkSession.builder().appName("test").master("local[*]").getOrCreate();
        jsc = new JavaSparkContext(spark.sparkContext());
//        schema = AvroConversionUtils.convertAvroSchemaToStructType(new Schema.Parser().parse(
//                readFromResource("AMRDEF.avsc")));
        // create empty dataset from schema
        inputDF = spark.read().parquet("src/test/resources/sample_data.parquet");
    }

    public static String readFromResource(String resourcePath) throws IOException {
        return IOUtils.toString(
                Objects.requireNonNull(
                        TestExampleTransformer.class
                                .getClassLoader()
                                .getResourceAsStream(resourcePath)
                ),
                StandardCharsets.UTF_8
        );
    }

    @Test
    public void testQuickStart(){
        ExampleTransformer transformer = new ExampleTransformer();
        TypedProperties properties = new TypedProperties();
        Dataset<Row> output = transformer.apply(jsc,spark,inputDF,properties);
    }


}