package postas

import participantes.Participante
import postas.requerimientos.PesoMinimo

case class Pesca(requerimiento: Option[PesoMinimo] = None) extends Posta{
  def criterioPosta(participante: Participante): Int =
    participante.cuantoPuedeCargar

  val cuantaHambreDa: Int = 5
}