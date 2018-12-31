package com.sina.sparksql
import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}
object TestHelloWord {
  case class XxOo(var id:Int,var name:String)
  def main(args: Array[String]): Unit = {

     val spark= SparkSession.builder().appName("SparkSql").master("local[1]").getOrCreate()
    import spark.implicits._
    val sparkview = spark.sparkContext.textFile("file:///F:/SparkSql.txt")
      .map(x => x.split(" "))
      .map(x => (new XxOo(x(0)toInt, x(1))))
      .toDF
     sparkview.createOrReplaceTempView("t_user")
    val prop = new Properties()
    prop.put("user","root")
    prop.put("password","root")
    spark.sql("select * from t_user ").orderBy($"id").write.format("jdbc").mode(SaveMode.Append)
      .jdbc("jdbc:mysql://localhost:3306/test","t_student",prop)

    

  }
}
