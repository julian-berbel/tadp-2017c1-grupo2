package torneos

import participantes.Inscripto

trait PasajeDeRondaEstandar[T <: Inscripto]{
  def criterioPasajeDeRonda: (List[T]) => List[T] = {competidores => competidores.take(competidores.length / 2)}
}