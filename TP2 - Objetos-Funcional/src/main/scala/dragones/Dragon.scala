package dragones

import participantes.Vikingo._
import postas.requerimientos.Requerimiento

abstract class Dragon(peso: Int, requerimientos: List[Requerimiento]) {
  def velocidadBasica: Int = 60

  val velocidad: Int = velocidadBasica - peso
  val danio: Int
  
  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    cuantoPuedeCargar > unVikingo.peso && requerimientos.forall(_.esCumplidoPor(unVikingo))

  val cuantoPuedeCargar: Int = peso / 5
}