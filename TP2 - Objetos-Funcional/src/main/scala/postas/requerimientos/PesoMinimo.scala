package postas.requerimientos

import participantes.Vikingo

case class PesoMinimo(peso: Int) extends Requerimiento{
  def esCumplidoPor(vikingo: Vikingo): Boolean =
    vikingo.cuantoPuedeCargar > peso
}