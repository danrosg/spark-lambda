val text : String ="Not another word count example"

var changeMe : String = "Another one"
changeMe = "Spark here I come"

def sayHello(name : String ) (implicit whoAreYou: () => String) = {
  s"Hello $name! My name is ${whoAreYou()}"
}

implicit def provideName() = { "Scala111" }

val result =sayHello("Daniel")

class fastTrack (val  name: String, var myself: String ) {

  def sayHello(name: String)(myself: String) = {
    s"Hello $name! My name is $myself"

  }
  val greeting =sayHello(name)(myself)
}
val fast=new fastTrack("test", "me")

println(fast.name)
fast.myself ="fast"





abstract class Person(fname: String , lname: String) {
  def fullName = {s"$fname-$lname"}

}

case class Student(fname: String , lname : String, id: Int)
  extends Person(fname,lname)

val me=Student ("Ahmad","Alkilani",23)

def getFullID[T <: Person](something: T) ={
  something match{
    case Student(fname, lname, id) => s"$fname-$lname-$id"
    case p:Person =>p.fullName
  }
}

getFullID(me)


implicit class stringUtils(myString : String){
  def scalaWordCount() ={
    val split =myString.split("\\s+")
    val grouped=split.groupBy(word=>word)
    val countPerKey =grouped.mapValues(group =>group.length)
    countPerKey
  }
}


"Spark collections mimic Scala collections".scalaWordCount()


val myList = List ( "Spark" , "mimics", "Scala")

val mapped = myList.map(s => s.toUpperCase)

val flatMapped =myList.flatMap{
  s => val filters =List("mimics","collections")
    if(filters.contains(s))
      None
    else
      Some(s)
}