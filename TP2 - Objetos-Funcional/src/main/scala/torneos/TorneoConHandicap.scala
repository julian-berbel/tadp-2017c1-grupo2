package torneos

import dragones.Dragon
import participantes.Vikingo.Vikingo
import postas.Posta

class TorneoConHandicap(postas: List[Posta], dragones: List[Dragon]) extends TorneoEstandar(postas, dragones){
  override def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos.reverse
}