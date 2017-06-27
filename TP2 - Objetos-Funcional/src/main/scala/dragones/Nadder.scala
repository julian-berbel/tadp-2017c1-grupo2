package main.scala.dragones

case class Nadder(peso: Int) extends Dragon(peso) 
{
  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
  {
      condicion1(unVikingo) && condicion2(unVikingo)
  }
  
  def condicion2(unVikingo: Vikingo): Boolean = unVikingo.danio < this.danio
  
  def danio: Int = 150
}