package pom;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	
	private SparkConf conf;
	private JavaSparkContext sc;
	private JavaPairRDD<String, String> rdd;

	@BeforeTest
	public void beforeMethod() {
		//SparkConf conf = new SparkConf().setAppName("PompuApp").set("spark.executor.memory", "16g"); // need to deComment this one for Cluster Mode
				
		conf = new SparkConf().setAppName("PompuApp").setMaster("local"); // this is for local mode when running from eclipse

		sc = new JavaSparkContext(conf);

		SQLContext sql = new SQLContext(sc);

		}

	@Test
	public void test1() {
		System.out.println("test1 Called");

		try { rdd=sc.wholeTextFiles("<LOCATION>");
		System.out.println(rdd.keys().count());
		} catch (Exception e) {
			System.out.println("-EXCEPTION "+e);
		}



	}

	@Test
	public void test2() {
		System.out.println("test2 Called");
		System.out.println(rdd.count());
		Assert.assertEquals(rdd.count(),30);
	}


	@AfterTest
	public void afterMethod() {
		sc.stop();
	}

}
