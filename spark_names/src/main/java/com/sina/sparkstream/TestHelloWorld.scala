package com.sina.sparkstream

import java.util.Properties

import org.apache.spark._
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.streaming._

object TestHelloWorld {
  def main(args: Array[String]): Unit = {
    var checkpoint="file:///D:/checkpoint"
    case class s(var key:String,var count:Long)
    val prop = new Properties()
    prop.put("user","root")
    prop.put("password","root")
    val conf = new SparkConf()
    conf.setAppName("SparkStream").setMaster("local[2]")
    var sc = new SparkContext(conf)
    sc.setLogLevel("FATAL")
    val sparkSession = SparkSession.builder().master("local[2]").appName("sparksql").getOrCreate()
    import sparkSession.implicits._
    var ssc = new StreamingContext(sc,Seconds(3))
   // ssc.checkpoint(checkpoint)
    ssc.socketTextStream("CentOS",9999)

      //updatestateByKey 做有状态的实时统计
      .flatMap(x=>x.split(" ")).map(s=>(s, 1)).reduceByKey(_+_).updateStateByKey(updateFunction).print()

       /* .foreachRDD(rdds=>{
          if(rdds.count()!=0){
            rdds.map(rdd=>((rdd._1,rdd._2))).toDF("kry","count")
            .write.format("jdbc")
              .mode(SaveMode.Append).jdbc("jdbc:mysql://localhost:3306/test","test_sparkstream",prop)
          }
        })*/

    ssc.start()
    ssc.awaitTermination()
  }
  val updateFunction:(Seq[Int],Option[Int])=>Option[Int]=(newValues,runningCount)=>{
    val newCount = runningCount.getOrElse(0)+newValues.sum
    Some(newCount)
  }
}
