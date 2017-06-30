package postas.requerimientos

import participantes.Participante

case class MonturaRequerida() extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    participante.tieneMontura
}