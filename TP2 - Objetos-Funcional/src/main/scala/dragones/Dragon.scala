package dragones

import participantes.Vikingo
import postas.requerimientos.Requerimiento

abstract class Dragon(peso: Int, requerimientos: List[Requerimiento]) {
  val velocidadBasica: Int = 60

  val velocidad: Int = velocidadBasica - peso
  val danio: Int
  
  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    cuantoPuedeCargar > unVikingo.peso && requerimientos.forall(_.esCumplidoPor(unVikingo))

  val cuantoPuedeCargar: Int = peso / 5
}