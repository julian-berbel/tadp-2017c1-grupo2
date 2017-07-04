package participantes

import Item.TipoDeItem
import postas.Posta

import scala.util.Try

trait Participante{
  val barbarosidad: Int

  def tieneUn(tipoDeItem: TipoDeItem): Boolean

  val cuantoPuedeCargar: Int

  val velocidad: Int

  val danio: Int

  val hambre: Int

  def puedeCorrer(posta: Posta): Boolean =
    posta.cumpleRequisitos(this) && correr(posta).isSuccess

  def correr(posta: Posta): Try[Participante]

  val tieneMontura: Boolean

  def deltaHambre(delta: Int): Participante

  def esMejorQue(otroParticipante: Participante)(posta: Posta): Boolean =
    posta.evaluar(this) > posta.evaluar(otroParticipante)
}
