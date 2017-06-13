package postas.requerimientos

import participantes.Participante

case class PesoMinimo(peso: Int) extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    participante.cuantoPuedeCargar > peso
}