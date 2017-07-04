package participantes

import Vikingo._

case class Equipo(miembros: List[Vikingo]) extends Inscripto {
  override def prepararse: List[Vikingo] = miembros

  val leQuedanMiembros: Boolean =
    miembros.nonEmpty
}
