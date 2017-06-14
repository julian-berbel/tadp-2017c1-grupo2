package postas.requerimientos

import participantes.Participante

case class BarbaridadMinima(barbaridad: Int) extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    participante.barbarosidad > barbaridad
}