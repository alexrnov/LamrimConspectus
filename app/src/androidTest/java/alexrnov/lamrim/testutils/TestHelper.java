package alexrnov.lamrim.testutils;

import org.junit.Assert;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class TestHelper {

  private static String BASIC_SAMPLE_PACKAGE = "alexrnov.lamrim";

  public static UiDevice mDevice = UiDevice.getInstance(getInstrumentation());

  public static UiObject byId(String id) {
    UiObject uiObject = mDevice.findObject(new UiSelector()
            .resourceId(BASIC_SAMPLE_PACKAGE + ":id/" + id));
    return uiObject;
  }


  public static String viewId(String id) {
    return BASIC_SAMPLE_PACKAGE + ":id/" + id;
  }


  public static void verifyText(String id, String title) throws Exception {
    UiObject tvActionBarTitle = mDevice.findObject(new UiSelector()
            .resourceId(BASIC_SAMPLE_PACKAGE + ":id/" + id));
    Assert.assertEquals(title, tvActionBarTitle.getText());
  }

  public static void clickAndWait(String id) throws Exception {
    UiObject cancelButton = TestHelper.byId(id);
    cancelButton.clickAndWaitForNewWindow();
  }


  public static String resourceId(String resourceId) {
    return BASIC_SAMPLE_PACKAGE + ":id/" + resourceId;
  }

  public static UiSelector getRowItem(int itemIndex, String resourceId) {
    return new UiSelector()
            .resourceId(TestHelper.resourceId(resourceId))
            .enabled(true).instance(itemIndex);
  }

}
