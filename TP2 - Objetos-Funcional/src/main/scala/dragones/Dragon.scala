package main.scala.dragones
import vikingos.Vikingo

abstract class Dragon(peso: Int) {
  def velocidadBasica: Int = 60
  
  def velocidad: Int = velocidadBasica - peso
  def danio: Int
  
  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    cuantoPuedeCargar > unVikingo.peso
  
  def cuantoPuedeCargar: Int = peso / 5
}