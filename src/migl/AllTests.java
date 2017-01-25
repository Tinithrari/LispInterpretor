package migl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import migl.util.ConsListTest;
import migl.util.ConsTest;
import migl.util.MoreConsListTest;

@RunWith(Suite.class)
@SuiteClasses({ ConsTest.class, ConsListTest.class, MoreConsListTest.class })
public class AllTests {
}
