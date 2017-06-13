package postas

import participantes.Participante
import requerimientos._

trait Posta{
  val requerimiento: Option[Requerimiento]
  val cuantaHambreDa: Int

  def correr(participantes: List[Participante]): List[Participante] =
    participantes.filter(_.puedeCorrer(this)).sortBy(criterioPosta).map(this(_))

  def criterioPosta(participante: Participante): Int

  def cumpleRequisitos(participante: Participante): Boolean =
    requerimiento.forall(_.esCumplidoPor(participante))

  def apply(participante: Participante): Participante =
    participante.deltaHambre(cuantaHambreDa)
}