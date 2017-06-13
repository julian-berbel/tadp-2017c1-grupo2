package postas.requerimientos

import participantes.Participante

case object MonturaRequerida extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    participante.tieneMontura
}