package debox.test

import org.scalatest.FunSuite

class MapTest extends FunSuite {

  import debox.{Map => DMap}

  test("apply") {
    val m = DMap.empty[Int, Int]
    assert(m.contains(2) === false)
    assert(m.get(2) === None)
    assert(m.get(5) === None)
    assert(m.size === 0)

    m(2) = 3
    assert(m.contains(2) === true)
    assert(m(2) === 3)
    assert(m.get(2) === Some(3))
    assert(m.get(5) === None)
    assert(m.size === 1)

    m(2) = 4
    assert(m.contains(2) === true)
    assert(m(2) === 4)
    assert(m.get(2) === Some(4))
    assert(m.get(5) === None)
    assert(m.size === 1)

    m(5) = 6
    assert(m.contains(5) === true)
    assert(m(5) === 6)
    assert(m.get(2) === Some(4))
    assert(m.get(5) === Some(6))
    assert(m.size === 2)

    m.remove(5)
    assert(m.contains(5) === false)
    assert(m.get(2) === Some(4))
    assert(m.get(5) === None)
    assert(m.size === 1)
  }

  test("resize/remove") {
    val m = DMap.empty[Int, Double]
    assert(m.size === 0)
    assert(m.contains(9) === false)
    assert(m.contains(11) === false)
    assert(m.contains(100) === false)
    for (i <- 0 until 100) {
      m(i) = i.toDouble * 7
    }
    assert(m.size === 100)
    assert(m(9) === 63.0)
    assert(m(11) === 77.0)
    assert(m.contains(100) === false)
    for (i <- 0 until 100) {
      m.remove(i)
    }
    assert(m.size === 0)
    assert(m.contains(9) === false)
    assert(m.contains(11) === false)
    assert(m.contains(100) === false)
    for (i <- 0 until 100) {
      m(i) = i.toDouble * 9
    }
    assert(m.size === 100)
    assert(m(9) === 81.0)
    assert(m(11) === 99.0)
    assert(m.contains(100) === false)
    for (i <- 0 until 100) {
      m.remove(i)
    }
    assert(m.size === 0)
    assert(m.contains(9) === false)
    assert(m.contains(11) === false)
    assert(m.contains(100) === false)
  }
}
