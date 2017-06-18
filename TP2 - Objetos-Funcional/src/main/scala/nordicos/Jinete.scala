package main.scala
import items.item._
import dragones.Dragon

case class Jinete(peso: Int, item: Item, barbarosidad: Int, hambre: Int, vel: Int, unDragon:Dragon) 
     extends Nordico(item: Item) 
{  
  def puedeCargar: Int = peso - unDragon.cuantoPuedeCargar
  
  var danio = barbarosidad + unDragon.danio
  
  def hambre(inc: Int): Jinete = copy(hambre = hambre + inc)
  
  def velocidad: Int = unDragon.velocidad - peso
}