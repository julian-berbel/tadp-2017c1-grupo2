package postas.requerimientos

import participantes.Vikingo

trait Requerimiento{
  def esCumplidoPor(vikingo: Vikingo): Boolean
}