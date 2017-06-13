package postas.requerimientos

import participantes.Vikingo

case class BarbaridadMinima(barbaridad: Int) extends Requerimiento{
  def esCumplidoPor(vikingo: Vikingo): Boolean =
    vikingo.barbarosidad > barbaridad
}