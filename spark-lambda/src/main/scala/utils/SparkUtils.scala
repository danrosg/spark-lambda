package utils

import java.lang.management.ManagementFactory

import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.{Duration, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}



/**
  * Created by danielrosales on 10/7/17.
  */
object SparkUtils {

  val isIDE = {
    ManagementFactory.getRuntimeMXBean.getInputArguments.toString().contains("IntelliJ IDEA")
  }

  def getSparkContext(appName: String) = {


    var checkpointDirectory = ""

    val conf = new SparkConf()
      .setAppName("Lambda with Spark")

    if (isIDE) {

      conf.setMaster("local[*]")
      checkpointDirectory = "/Users/danielrosales/temp"

    } else {
      checkpointDirectory = "hdfs://lamda-pluralsight:9000/spark/checkpoint"
    }

    val sc = SparkContext.getOrCreate(conf)
    sc.setCheckpointDir(checkpointDirectory)
    sc

  }

  def getSQLContext(sc: SparkContext) = {
    val sqlContext = SQLContext.getOrCreate(sc)
    sqlContext
  }

  def getStreamingContext(streamingApp: (SparkContext,Duration) => StreamingContext, sc: SparkContext, batchDuration: Duration) ={

    val creatingFunc = () => streamingApp(sc,batchDuration)
    val ssc = sc.getCheckpointDir match {
      case Some(checkpointDir) => StreamingContext.getActiveOrCreate(checkpointDir,creatingFunc,sc.hadoopConfiguration,createOnError = true)
      case None => StreamingContext.getActiveOrCreate(creatingFunc)
    }
    sc.getCheckpointDir.foreach(cp => ssc.checkpoint(cp))
    ssc
  }

}
