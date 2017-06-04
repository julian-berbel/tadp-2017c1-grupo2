package main.scala.dragones

case class Gronckle(peso: Int) extends Dragon(peso) {
  override def velocidadBasica: Int = super.velocidadBasica / 2
  
  def danio: Int = peso * 5
}