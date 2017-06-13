package participantes

import Item.Item
import postas.Posta

import scala.util.Try

trait Participante {
  val barbarosidad: Int

  def tieneUn[T>:Item](_item: T): Boolean

  val cuantoPuedeCargar: Int

  val velocidad: Int

  val danio: Int

  val hambre: Int

  def puedeCorrer(posta: Posta): Boolean =
    posta.cumpleRequisitos(this) && Try(correr(posta).hambre).isSuccess

  def correr(posta: Posta): Participante =
    posta(this)

  val tieneMontura: Boolean

  def deltaHambre(delta: Int): Participante
}
