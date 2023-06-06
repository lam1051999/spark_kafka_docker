package extensions

import org.apache.spark.sql.SparkSessionExtensions

class AllExtensions extends (SparkSessionExtensions => Unit) {
  override def apply(ext: SparkSessionExtensions): Unit = {
    ext.injectOptimizerRule(CustomProjectFilterExtension)
  }
}








