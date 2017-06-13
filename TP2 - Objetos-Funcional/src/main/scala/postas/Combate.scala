package postas

import participantes.Participante
import postas.requerimientos.BarbaridadMinima

case class Combate(requerimiento: Some[BarbaridadMinima]) extends Posta{
  def criterioPosta(participante: Participante): Int =
    participante.danio

  val cuantaHambreDa: Int = 10
}