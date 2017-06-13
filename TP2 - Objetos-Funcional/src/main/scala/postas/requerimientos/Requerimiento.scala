package postas.requerimientos

import participantes.Participante

trait Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean
}