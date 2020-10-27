package model

import model.levelComponent.levelBaseImpl.{Level1, Level2, Level3}
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
      var level1 = new Level1
      var level2 = new Level2
      var level3 = new Level3
      var fileIOXML = new model.fileIOComponent.fileIO_XML_Impl.FileIO
      "Level1" in {
        fileIOXML.save(level1) should be(fileIOXML.save(level1))
        fileIOXML.load.getName should be(level1.getName)
      }
      "Level2" in {
        fileIOXML.save(level2) should be(fileIOXML.save(level2))
        fileIOXML.load.getName should be(level2.getName)
      }
      "Level3" in {
        fileIOXML.save(level3) should be(fileIOXML.save(level3))
        fileIOXML.load.getName should be(level3.getName)
      }
    }
  }
  "JSON" when {
    "new" should {
      var level1 = new Level1
      var level2 = new Level2
      var level3 = new Level3
      var fileIOJson = new model.fileIOComponent.fileIO_Json_Impl.FileIO
      "Level1" in {
        fileIOJson.save(level1) should be(fileIOJson.save(level1))
        fileIOJson.load.getName should be(level1.getName)
      }
      "Level2" in {
        fileIOJson.save(level2) should be(fileIOJson.save(level2))
        fileIOJson.load.getName should be(level2.getName)
      }
      "Level3" in {
        fileIOJson.save(level3) should be(fileIOJson.save(level3))
        fileIOJson.load.getName should be(level3.getName)
      }
    }
  }
}
