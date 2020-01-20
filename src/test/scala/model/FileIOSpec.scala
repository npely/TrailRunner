package model

import model.levelComponent.levelBaseImpl.Level1
import model.levelComponent.levelMockImpl.Level
import org.scalatest.{Matchers, WordSpec}

class FileIOSpec extends WordSpec with Matchers {
  "FileIO" when {
    "new" should {
      var level = new Level
      var fileio = new model.fileIOComponent.fileIOMockImpl.FileIO
        "test" in {
          fileio.save(level) should be()
          fileio.load.getName should be(level.getName)
        }
    }
  }
  "XML" when {
    "new" should {
      var level = new Level1
      var fileIOXML = new model.fileIOComponent.fileIO_XML_Impl.FileIO
      "test" in {
        fileIOXML.save(level) should be(fileIOXML.save(level))
        fileIOXML.load.getName should be(level.getName)
      }
    }
  }
}
