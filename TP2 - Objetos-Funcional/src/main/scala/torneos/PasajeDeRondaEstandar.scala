package torneos

import participantes.Vikingo.Vikingo

trait PasajeDeRondaEstandar{
  def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {competidores => competidores.take(competidores.length / 2)}
}