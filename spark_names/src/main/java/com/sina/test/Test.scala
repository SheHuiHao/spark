package com.sina.test

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable


object Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("names").setMaster("local[2]")
    conf.set("spark.testing.memory", "2147480000")
    val sc = new SparkContext(conf)


    sc.wholeTextFiles("file///F://names").map(t=>(str(t._1),t._2.split("\r\n")))
      .map(t=>(t._1,line(t._2))).map(t=>(t._1, t._2.groupBy(t=>t._1).map(t=>(t._1,t._2.unzip._2.sum))))
      .collect().foreach(println)

    //---------------------------------------------------------------------------------------

   // sc.wholeTextFiles("file///F://names").collect().foreach(t=>println(t._1))

  }
  def str(s:String):String={
    val strings = s.split("/")

    strings(3).substring(3,7)
  }

  def line(arr:Array[String])={
    var map=mutable.HashMap[String,Int]()
    for(i<-arr){
      val str = i.split(",")(1)
      val str2 = i.split(",")(2).toInt
      /*val str = strings(1)
      val str2 = strings(2).toInt*/
      map.put(str,str2)
    }
      map.toList
  }
}
