package main.scala
import items.item._

abstract class Nordico(item: Item) {

  val hambre: Int = 0
  def tieneUn(_item: Item) = item == _item
  
  def puedeCargar: Int
  
  var danio: Int
    
  def calcularDanio(dani :Int): Nordico = {danio = danio + dani
                                           return this}
  
  def hambre(inc: Int): Nordico
}