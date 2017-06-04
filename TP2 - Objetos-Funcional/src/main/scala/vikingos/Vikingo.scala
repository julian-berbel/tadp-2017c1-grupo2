package main.scala.vikingos
import items.item._

case class Vikingo(peso: Int, item: Item, danio: Int, hambre: Int = 0) {
  def tieneUn(_item: Item) = item == _item
  
  def danio(dani: Int): Vikingo = copy(danio = dani + danio)
  
  def hambre(hambre: Int): Vikingo = copy(hambre = hambre + danio)
}

object hipo extends Vikingo(peso = ???, item = sistemaDeVuelo(_), danio = ???)

object astrid extends Vikingo(peso = ???, item = hacha(30), danio = ???)

object patan extends Vikingo(peso = ???, item = maza(100), danio = ???)

object pataPez extends Vikingo(peso = ???, item = comestible(???), danio = ???)