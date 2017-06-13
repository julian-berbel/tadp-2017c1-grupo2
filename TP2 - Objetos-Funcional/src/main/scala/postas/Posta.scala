package postas

import participantes.{Jinete, Vikingo}
import requerimientos._

trait Posta{
  val requerimiento: Option[Requerimiento]
  val cuantaHambreDa: Int

  def correr(vikingos: List[Vikingo]): List[Vikingo] =
    vikingos.filter(_.puedeCorrer(this)).sortBy(criterioPosta).map(darHambre(_))

  def criterioPosta(vikingo: Vikingo): Int

  def cumpleRequisitos(vikingo: Vikingo): Boolean =
    requerimiento.forall(_.esCumplidoPor(vikingo))

  def darHambre(vikingo: Vikingo): Vikingo =
    vikingo.deltaHambre(vikingo match {
      case (Jinete(_,_)) => 5
      case _ => this.cuantaHambreDa
    })
}