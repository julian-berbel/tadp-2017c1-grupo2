package participantes

case class Equipo(miembros: List[Vikingo]) extends Inscripto {
  override def prepararse: List[Vikingo] = miembros
}
