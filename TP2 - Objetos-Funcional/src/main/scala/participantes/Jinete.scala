package participantes

import Item.Item
import dragones.Dragon
import postas.Posta

import scala.util.Try

case class Jinete(vikingo: Vikingo, _dragon: Some[Dragon]) extends Participante{
  val dragon: Dragon = _dragon.get
  require(dragon.puedeSerMontadoPor(vikingo))

  def tieneUn[T<:Item](_item: T): Boolean =
    vikingo.tieneUn(_item)

  def deltaHambre(delta: Int): Jinete =
    copy(vikingo = vikingo.deltaHambre(delta))

  def correr(posta: Posta): Try[Jinete] =
    Try(deltaHambre(5))

  val hambre = vikingo.hambre
  val barbarosidad = vikingo.barbarosidad

  override val cuantoPuedeCargar: Int = dragon.cuantoPuedeCargar - vikingo.peso
  
  override val danio: Int = vikingo.danio + dragon.danio
  
  override val velocidad: Int = dragon.velocidad - vikingo.peso

  override val tieneMontura: Boolean = true
}