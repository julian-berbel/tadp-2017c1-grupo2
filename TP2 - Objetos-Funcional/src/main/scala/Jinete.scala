package main.scala
import items.item._
import vikingos.Vikingo
import dragones.Dragon

case class Jinete(unDragon:Dragon) 
  extends Vikingo(peso: Int, item: Item, barbarosidad: Int, velocidad: Int) {
  
  override def puedeCargar: Int = this.peso - unDragon.cuantoPuedeCargar
  
  def velocidad2: Int = copy(velocidad = unDragon.velocidad - this.peso)
  
}