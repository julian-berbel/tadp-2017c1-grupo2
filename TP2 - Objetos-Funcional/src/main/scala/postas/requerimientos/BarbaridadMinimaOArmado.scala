package postas.requerimientos

import Item.Arma
import participantes.Vikingo

case class BarbaridadMinimaOArmado(barbaridad: Int) extends Requerimiento{
  def esCumplidoPor(vikingo: Vikingo): Boolean =
    BarbaridadMinima(barbaridad).esCumplidoPor(vikingo) || vikingo.tieneUn(Arma)
}