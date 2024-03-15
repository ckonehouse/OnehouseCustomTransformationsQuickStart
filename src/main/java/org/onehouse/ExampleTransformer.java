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
        df = df.select("trip_distance", "fare_amount","VendorId").groupBy(df.col("VendorId")).agg(functions.min("fare_amount")); //sample aggregation that returns two columns based on the minimum id value
        //You can add any transformation that you want here. The return value of this apply function is what is written to the destination table.
        return df;
    }
}