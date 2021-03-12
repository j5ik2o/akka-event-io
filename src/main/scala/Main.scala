import jnr.ffi.LibraryLoader

import java.io.File
import java.lang.System.mapLibraryName

trait RustLib {
  def double_input(i: Int): Int
}

object Main {

  def getLibraryPath(dylib: String): String = {
    val f = new File(getClass.getClassLoader.getResource(mapLibraryName(dylib)).getFile)
    f.getParent
  }

  def main(args: Array[String]): Unit = {
    val dylib = "double_input"
    System.setProperty("jnr.ffi.library.path", getLibraryPath(dylib))

    val rlib = LibraryLoader.create(classOf[RustLib]).load(dylib)
    val r    = rlib.double_input(20)

    System.out.println("Result from rust double_input:  " + r)
  }

}
