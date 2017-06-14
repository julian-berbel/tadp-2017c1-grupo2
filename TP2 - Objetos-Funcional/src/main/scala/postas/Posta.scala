package postas

import participantes.{Jinete, Participante, Vikingo}
import requerimientos._

trait Posta{
  val requerimiento: Option[Requerimiento]
  val cuantaHambreDa: Int

  def correr(participantes: List[Participante]): List[Participante] =
    participantes.filter(_.puedeCorrer(this)).sortBy(criterioPosta).map(_.correr(this).get)

  def criterioPosta(participante: Participante): Int

  def cumpleRequisitos(participante: Participante): Boolean =
    requerimiento.forall(_.esCumplidoPor(participante))

  def darHambre(vikingo: Vikingo): Vikingo =
    vikingo.deltaHambre(this.cuantaHambreDa)
}