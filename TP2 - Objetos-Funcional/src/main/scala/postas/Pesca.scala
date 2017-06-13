package postas

import participantes.Vikingo
import postas.requerimientos.PesoMinimo

case class Pesca(requerimiento: Option[PesoMinimo] = None) extends Posta{
  def criterioPosta(vikingo: Vikingo): Int =
    vikingo.cuantoPuedeCargar

  val cuantaHambreDa: Int = 5
}