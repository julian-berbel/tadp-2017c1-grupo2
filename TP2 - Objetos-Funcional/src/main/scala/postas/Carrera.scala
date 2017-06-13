package postas

import participantes.Participante
import requerimientos.MonturaRequerida

case class Carrera(requerimiento: Option[MonturaRequerida], largo: Int) extends Posta{
  def criterioPosta(participante: Participante): Int =
    participante.velocidad

  val cuantaHambreDa: Int = largo
}