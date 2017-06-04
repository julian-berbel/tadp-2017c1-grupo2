package main.scala.dragones
import items.item._
import vikingos.Vikingo

case class FuriaNocturna(peso: Int, danio: Int) extends Dragon(peso) {
  override def velocidadBasica: Int = super.velocidadBasica * 3
}

object chimuelo extends FuriaNocturna(???, ???){
  override def puedeSerMontadoPor(unVikingo: Vikingo) = unVikingo.tieneUn(sistemaDeVuelo) && super.puedeSerMontadoPor(unVikingo)
}