package postas.requerimientos

import participantes.Vikingo

case object MonturaRequerida extends Requerimiento{
  def esCumplidoPor(vikingo: Vikingo): Boolean =
    vikingo.tieneMontura
}