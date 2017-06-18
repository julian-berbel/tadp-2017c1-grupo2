package main.scala
import items.item._
import dragones.Dragon

case class Jinete(peso: Int, item: Item, barbarosidad: Int, hambre: Int, velocidad: Int, unDragon:Dragon) 
     extends Nordico(item: Item) 
{  
  def puedeCargar: Int = peso - unDragon.cuantoPuedeCargar
  
  var danio = barbarosidad + unDragon.danio
  
  def velocidad2: Jinete = copy(velocidad = unDragon.velocidad - peso)  
}