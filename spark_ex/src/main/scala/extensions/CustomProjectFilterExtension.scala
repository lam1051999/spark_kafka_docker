package extensions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.catalyst.rules.Rule

case class CustomProjectFilterExtension(spark: SparkSession)
  extends Rule[LogicalPlan] {
  override def apply(plan: LogicalPlan): LogicalPlan = {
    val fixedPlan = plan transformDown {
      case Project(expression, Filter(condition, child)) =>
          Filter(condition, child)
    }
    fixedPlan
  }
}









