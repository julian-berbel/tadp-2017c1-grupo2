package participantes

import dragones.Dragon

case class Jinete(vikingo: Vikingo, dragon: Dragon) extends Vikingo(vikingo.caracteristicas){
  require(dragon.puedeSerMontadoPor(vikingo))

  override val cuantoPuedeCargar: Int = dragon.cuantoPuedeCargar - peso
  
  override val danio: Int = super.danio + dragon.danio
  
  override val velocidad: Int = dragon.velocidad - peso

  override val tieneMontura: Boolean = true
}