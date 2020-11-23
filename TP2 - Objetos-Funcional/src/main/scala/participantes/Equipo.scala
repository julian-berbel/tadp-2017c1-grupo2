package participantes

import scala.util.Try

import Vikingo._

case class Equipo(miembros: List[Vikingo]) extends Inscripto {
  require(miembros.nonEmpty, "Un equipo no puede quedar vacío")

  override def prepararse: List[Vikingo] = miembros

  def selectMembers(vikingos: List[Vikingo]): Option[Equipo] =
    Try(copy(miembros = miembros.filter{miembro => vikingos.map(_.nombre).contains(miembro.nombre)})).toOption
}
