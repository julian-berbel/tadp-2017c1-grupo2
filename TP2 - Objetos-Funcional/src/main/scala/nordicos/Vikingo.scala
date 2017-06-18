package main.scala.vikingos
import items.item._

case class Vikingo(peso: Int, item: Item, barbarosidad: Int, hambre: Int, velocidad: Int)
     extends Nordico(item: Item)
{ 
  def puedeCargar: Int = peso/2 + barbarosidad 
  
  var danio = barbarosidad
  
  def hambre(inc: Int): Vikingo = copy(hambre = hambre + inc)
}

object hipo extends Vikingo(peso = ???, item = sistemaDeVuelo(_), barbarosidad = ???, velocidad = ???, hambre = ???)

object astrid extends Vikingo(peso = ???, item = hacha(30), barbarosidad = ???, velocidad = ???, hambre = ???)

object patan extends Vikingo(peso = ???, item = maza(100), barbarosidad = ???, velocidad = ???, hambre = ???)

object pataPez extends Vikingo(peso = ???, item = comestible(???), barbarosidad = ???, velocidad = ???, hambre = ???)