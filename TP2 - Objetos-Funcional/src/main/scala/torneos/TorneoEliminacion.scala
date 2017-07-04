package torneos

import dragones.Dragon
import participantes.Vikingo.Vikingo
import postas.Posta

class TorneoEliminacion(postas: List[Posta], dragones: List[Dragon], cuantosCaen: Int) extends TorneoEstandar(postas, dragones){
  override def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {vikingos => vikingos.dropRight(cuantosCaen)}
}