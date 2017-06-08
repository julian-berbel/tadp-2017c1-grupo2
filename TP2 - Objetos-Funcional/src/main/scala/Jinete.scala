package main.scala
import items.item._
import vikingos.Vikingo
import dragones.Dragon

class Jinete(unDragon:Dragon) extends Vikingo(peso: Int, 
                                              item: Item, 
                                              barbarosidad: Int, 
                                              hambre: Int, 
                                              velocidad: Int) 
{  
  override def puedeCargar: Int = this.peso - unDragon.cuantoPuedeCargar
  
  override def danio: Int = super.danio + unDragon.danio 
  
  def velocidad: Vikingo = copy(velocidad = unDragon.velocidad - this.peso)  
}