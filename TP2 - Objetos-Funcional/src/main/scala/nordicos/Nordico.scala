package main.scala
import items.item._

abstract class Nordico(item: Item) 
{
  def hambre: Int
  
  def velocidad: Int
  
  def tieneUn(_item: Item) = item == _item
  
  def puedeCargar: Int
  
  var danio: Int
    
  def calcularDanio(dani :Int): Nordico = {danio = danio + dani
                                           return this}
  
  def hambre(inc: Int): Nordico
}