package main.scala.vikingos
import items.item._

case class Vikingo(peso: Int, item: Item, barbarosidad: Int, hambre: Int = 0, velocidad: Int, descalificado: Boolean = false) 
{
  def tieneUn(_item: Item) = item == _item
  
  def puedeCargar: Int = peso/2 + barbarosidad 
  
  var danio = barbarosidad
    
  def calcularDanio(dani :Int): Vikingo = {danio = danio + dani
                                           return this}
  
  def hambre(inc: Int): Vikingo = copy(hambre = hambre + inc)
  
  def descalificar: Vikingo = copy(descalificado = true)
}

object hipo extends Vikingo(peso = ???, item = sistemaDeVuelo(_), barbarosidad = ???, velocidad = ???)

object astrid extends Vikingo(peso = ???, item = hacha(30), barbarosidad = ???, velocidad = ???)

object patan extends Vikingo(peso = ???, item = maza(100), barbarosidad = ???, velocidad = ???)

object pataPez extends Vikingo(peso = ???, item = comestible(???), barbarosidad = ???, velocidad = ???)