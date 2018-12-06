import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	BlockTest.class,
	PageTest.class,
	DiscTest.class,
	MainMemTest.class,
	L2CacheTest.class
})
public class MHSTestSuite {
}
