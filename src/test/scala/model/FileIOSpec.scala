package model

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
}
