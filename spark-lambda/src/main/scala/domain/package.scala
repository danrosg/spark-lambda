/**
  * Created by danielrosales on 9/23/17.
  */
package object domain {

  case class Activity(timestamp_hour: Long,
                      referrer: String,
                      action: String,
                      prevPage: String,
                      page: String,
                      visitor: String,
                      product: String,
                      inputPropos: Map[String, String] = Map()
                     )


  case class ActivityByProduct(product: String,
                               timestamp_hour: Long,
                               purchase_count: Long,
                               add_to_cart_count: Long,
                               page_view_count: Long)

}