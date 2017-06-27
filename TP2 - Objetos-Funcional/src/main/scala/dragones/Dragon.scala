package main.scala.dragones
import vikingos.Vikingo

type Criterio = Vikingo => Boolean

abstract class Dragon(peso: Int) 
  {
    def velocidadBasica: Int = 60
  
    def velocidad: Int = velocidadBasica - peso
    def danio: Int
  
    def puedeSerMontadoPor(unVikingo: Vikingo): Boolean
    
    def cuantoPuedeCargar: Int = peso / 5
  
    def condicion1(unVikingo: Vikingo): Boolean = cuantoPuedeCargar > unVikingo.peso
    
    def barbarosidadMinima(min:Int, v: Vikingo):Boolean
}   