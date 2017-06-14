package postas

import participantes.Participante
import requerimientos.MonturaRequerida

case class Carrera(largo: Int, requerimiento: Option[MonturaRequerida] = None) extends Posta{
  def criterioPosta(participante: Participante): Int =
    participante.velocidad

  val cuantaHambreDa: Int = largo
}