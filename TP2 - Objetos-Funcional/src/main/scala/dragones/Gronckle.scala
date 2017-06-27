package main.scala.dragones

case class Gronckle(peso: Int, min: Int) extends Dragon(peso) 
{
  override def velocidadBasica: Int = super.velocidadBasica / 2
  
  def danio: Int = peso * 5
  
  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
  {
      condicion1(unVikingo) && condicion2(unVikingo, min)
  }
  
  def condicion2(unVikingo: Vikingo, min: Int): Boolean = unVikingo.peso < min 
}