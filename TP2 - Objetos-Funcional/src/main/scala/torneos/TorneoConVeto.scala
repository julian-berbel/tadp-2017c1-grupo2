package torneos

import dragones.Dragon
import postas.Posta

class TorneoConVeto(postas: List[Posta], dragones: List[Dragon], condicionVeto: Dragon => Boolean) extends TorneoEstandar(postas, dragones){
  override val dragonesDisponibles: List[Dragon] = dragones.filter(condicionVeto)
}