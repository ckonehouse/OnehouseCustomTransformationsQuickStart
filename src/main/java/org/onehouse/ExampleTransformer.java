package org.onehouse;


import org.apache.hudi.common.config.TypedProperties;
import org.apache.hudi.utilities.transform.Transformer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;


public class ExampleTransformer implements Transformer {
    private static final Logger log = LogManager.getLogger(ExampleTransformer.class);


    public Dataset<Row> apply(JavaSparkContext javaSparkContext, SparkSession sparkSession, Dataset<Row> df, TypedProperties typedProperties) {

        return df;
    }
}