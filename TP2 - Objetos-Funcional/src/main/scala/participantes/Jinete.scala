package participantes

import dragones.Dragon

case class Jinete(dragon: Dragon, vikingo: Vikingo) extends Participante{
  require(dragon.puedeSerMontadoPor(vikingo))

  def tieneUn[T >: Item.Item](_item: T): Boolean =
    vikingo.tieneUn(_item)

  val barbarosidad: Int = vikingo.barbarosidad

  val cuantoPuedeCargar: Int = dragon.cuantoPuedeCargar - vikingo.peso
  
  val danio: Int = vikingo.danio + dragon.danio
  
  val velocidad: Int = dragon.velocidad - vikingo.peso

  val tieneMontura: Boolean = true

  val hambre: Int = vikingo.hambre

  def deltaHambre(delta: Int): Jinete = copy(vikingo = vikingo.deltaHambre(delta))
}