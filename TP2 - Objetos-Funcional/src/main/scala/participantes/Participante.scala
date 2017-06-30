package participantes

import Item.Item
import dragones.Dragon
import postas.Posta

import scala.util.Try

trait Participante{
  val barbarosidad: Int

  def tieneUn[T<:Item](_item: T): Boolean

  val cuantoPuedeCargar: Int

  val velocidad: Int

  val danio: Int

  val hambre: Int

  def puedeCorrer(posta: Posta): Boolean =
    posta.cumpleRequisitos(this) && correr(posta).isSuccess

  def correr(posta: Posta): Try[Participante]

  val tieneMontura: Boolean

  def deltaHambre(delta: Int): Participante

  val _dragon: Option[Dragon]

  val vikingo: Vikingo
}
